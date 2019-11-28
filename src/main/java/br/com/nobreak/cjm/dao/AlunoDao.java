package br.com.nobreak.cjm.dao;


import br.com.nobreak.cjm.model.Aluno;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class AlunoDao {

    Session sessao;
    DAO<Aluno> dao;

    public AlunoDao(Session sessao) {
        this.sessao = sessao;
        this.dao = new DAO<Aluno>(Aluno.class, this.sessao);
    }

    public Boolean registre(Aluno aluno) {
        Boolean resultado = false;
        StringBuilder query = new StringBuilder("INSERT INTO alunos (id, email, senha, nome, endereco, bairro, cidade, estado, telefone, pontos, questoes, acertos, erros) VALUES (");
        query.append(aluno.getId());
        query.append(", '");
        query.append(aluno.getEmail());
        query.append("', '");
        query.append(aluno.getSenha());
        query.append("', '");
        query.append(aluno.getNome());
        query.append("', '");
        query.append(aluno.getEndereco());
        query.append("', '");
        query.append(aluno.getBairro());
        query.append("', '");
        query.append(aluno.getCidade());
        query.append("', '");
        query.append(aluno.getEstado());
        query.append("', '");
        query.append(aluno.getTelefone());
        query.append("', ");
        query.append(aluno.getPontos());
        query.append(", ");
        query.append(aluno.getQuestoes());
        query.append(", ");
        query.append(aluno.getAcertos());
        query.append(", ");
        query.append(aluno.getErros());
        query.append(");");
        try {
            sessao.execute(query.toString());
            resultado = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public Boolean atualizeEstatistica(Aluno aluno) {
        Boolean resultado = false;
        StringBuilder query = new StringBuilder("UPDATE alunos SET ");
        query.append("pontos=");
        query.append(aluno.getPontos());
        query.append(", questoes=");
        query.append(aluno.getQuestoes());
        query.append(", acertos=");
        query.append(aluno.getAcertos());
        query.append(", erros=");
        query.append(aluno.getErros());
        query.append("WHERE id=");
        query.append(aluno.getId());
        query.append(";");
        try {
            sessao.execute(query.toString());
            resultado = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public Boolean atualizeAluno(Aluno aluno) {
        Boolean resultado = false;
        StringBuilder query = new StringBuilder("UPDATE alunos SET ");
        query.append("email=");
        query.append(aluno.getEmail());
        query.append(", senha=");
        query.append(aluno.getSenha());
        query.append(", nome=");
        query.append(aluno.getNome());
        query.append(", endereco=");
        query.append(aluno.getEndereco());
        query.append(", bairro=");
        query.append(aluno.getBairro());
        query.append(", cidade=");
        query.append(aluno.getCidade());
        query.append(", estado=");
        query.append(aluno.getEstado());
        query.append(", telefone=");
        query.append(aluno.getTelefone());
        query.append("WHERE id=");
        query.append(aluno.getId());
        query.append(";");
        try {
            sessao.execute(query.toString());
            resultado = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public Aluno busquePorEmail(String email) {
        Aluno resultado = null;
        List<Aluno> alunos = construaListaAlunos(dao.buscarPorEmail(email));
        if (alunos.size() > 1) {
            return null;
        } else {
            for (Aluno aluno : alunos
            ) {
                resultado = aluno;
            }
            return resultado;
        }
    }

    public Aluno busquePorId(UUID id) {
        return construaAluno(dao.buscarPorId(id.toString()));
    }

    public List<Aluno> listeAlunos() {
        return construaListaAlunos(dao.listar());
    }

    public List<Aluno> busquePorNome(String nome) {
        List<Aluno> alunos = null;
        try {
            alunos = construaListaAlunos(dao.buscarPorNome(nome));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alunos;
    }

    private List<Aluno> construaListaAlunos(ResultSet resultado) {
        ArrayList<Aluno> alunos = new ArrayList<>();
        for (Row row:resultado) {
            alunos.add(construaAluno(row));
        }
        return alunos;
    }

    private Aluno construaAluno(Row resultado) {
        Aluno aluno = new Aluno();
        aluno.setId(resultado.getUUID("id"));
        aluno.setEmail(resultado.getString("email"));
        aluno.setSenha(resultado.getString("senha"));
        aluno.setNome(resultado.getString("nome"));
        aluno.setEndereco(resultado.getString("endereco"));
        aluno.setBairro(resultado.getString("bairro"));
        aluno.setCidade(resultado.getString("cidade"));
        aluno.setEstado(resultado.getString("estado"));
        aluno.setTelefone(resultado.getString("telefone"));
        aluno.setPontos(resultado.getInt("pontos"));
        aluno.setQuestoes(resultado.getInt("questoes"));
        aluno.setAcertos(resultado.getInt("acertos"));
        aluno.setErros(resultado.getInt("erros"));
        return aluno;
    }

}
