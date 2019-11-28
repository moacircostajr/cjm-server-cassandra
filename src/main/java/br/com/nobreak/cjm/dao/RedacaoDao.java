package br.com.nobreak.cjm.dao;

import br.com.nobreak.cjm.model.Redacao;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RedacaoDao {

    Session sessao;
    DAO<Redacao> dao;

    public RedacaoDao(Session sessao) {
        this.sessao = sessao;
        this.dao = new DAO<Redacao>(Redacao.class, this.sessao);
    }

    public Boolean registre(Redacao redacao) {
        StringBuilder query = new StringBuilder("INSERT INTO redacoes (id, id_aluno, tema, pontuacao");
        StringBuilder queryBusca = new StringBuilder(query.toString().replace("redacoes", "redacoes_busca"));
        query.append(", redacao_original, redacao_corrigida) VALUES (");
        queryBusca.append(") VALUES (");
        query.append(redacao.getId().toString()).append(", ").append(redacao.getIdAluno()).append(", '").append(redacao.getTema()).append("', ").append(redacao.getPontuacao());
        queryBusca.append(redacao.getId().toString()).append(", ").append(redacao.getIdAluno()).append(", '").append(redacao.getTema()).append("', ").append(redacao.getPontuacao());
        queryBusca.append(");");
        query.append(", textAsBlob('");
        query.append(redacao.getRedacaoOriginal());
        query.append("'), textAsBlob('");
        query.append(redacao.getRedacaoCorrigida());
        query.append("'));");
        query.insert(0, "BEGIN BATCH ");
        query.append(queryBusca.toString());
        query.append("APPLY BATCH;");
        return sessao.execute(query.toString()).wasApplied();
    }


    public Redacao busquePorId(UUID id) {
        StringBuilder query = new StringBuilder("SELECT id, id_aluno, tema, pontuacao, blobAsText(redacao_original), blobAsText(redacao_corrigida) FROM redacoes WHERE id=");
        query.append(id);
        query.append(";");
        return construaRedacao(sessao.execute(query.toString()).one());
    }

    public List<Redacao> busquePorIdAluno(UUID id){
        StringBuilder query = new StringBuilder("SELECT id, id_aluno, tema, pontuacao FROM redacoes_busca WHERE id_aluno='");
        query.append(id);
        query.append("';");
        return construaListaRedacoes(sessao.execute(query.toString()));
    }

    public List<Redacao> busquePorTema(String tema){
        StringBuilder query = new StringBuilder("SELECT id, id_aluno, tema, pontuacao FROM redacoes_busca WHERE tema='");
        query.append(tema);
        query.append("';");
        return construaListaRedacoes(sessao.execute(query.toString()));
    }

    private List<Redacao> construaListaRedacoes(ResultSet resultado) {
        ArrayList<Redacao> comentarios = new ArrayList<>();
        for (Row row:resultado) {
            comentarios.add(construaRedacao(row));
        }
        return comentarios;
    }

    private Redacao construaRedacao(Row resultado) {
        Redacao redacao = new Redacao();
        try {
            redacao.setId(resultado.getUUID("id"));
            redacao.setIdAluno(resultado.getUUID("id_aluno"));
            redacao.setTema(resultado.getString("tema"));
            redacao.setPontuacao(resultado.getInt("pontuacao"));
            redacao.setRedacaoOriginal(resultado.getString("system.blobastext(redacao_original)"));
            redacao.setRedacaoCorrigida(resultado.getString("system.blobastext(redacao_corrigida)"));
            return redacao;
        } catch (IllegalArgumentException ex) {
            redacao.setId(resultado.getUUID("id"));
            redacao.setIdAluno(resultado.getUUID("id_aluno"));
            redacao.setTema(resultado.getString("tema"));
            redacao.setPontuacao(resultado.getInt("pontuacao"));
            return redacao;
        }
    }

}
