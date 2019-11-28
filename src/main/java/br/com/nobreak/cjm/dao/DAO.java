package br.com.nobreak.cjm.dao;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class DAO<T> {

    private Session sessao;
    private Class classe;

    public DAO(Class<T> classe, Session session) {
        this.classe = classe;
        this.sessao = session;
    }

    public Row buscarPorId(String id) {
        StringBuffer stringBuffer = new StringBuffer("SELECT * FROM ");
        stringBuffer.append(convertaNomeClasse(classe.getName()));
        stringBuffer.append(" WHERE id = ");
        stringBuffer.append(id);
        stringBuffer.append(";");
        return sessao.execute(stringBuffer.toString()).one();
    }

    public ResultSet buscarPorEmail(String email) {
        StringBuffer stringBuffer = new StringBuffer("SELECT * FROM ");
        stringBuffer.append(convertaNomeClasse(classe.getName()));
        stringBuffer.append(" WHERE email = '");
        stringBuffer.append(email);
        stringBuffer.append("';");
        return sessao.execute(stringBuffer.toString());
    }

    public ResultSet buscarPorNome(String nome) {
        StringBuffer stringBuffer = new StringBuffer("SELECT * FROM ");
        stringBuffer.append(convertaNomeClasse(classe.getName()));
        stringBuffer.append(" WHERE nome = '");
        stringBuffer.append(nome);
        stringBuffer.append("';");
        return sessao.execute(stringBuffer.toString());
    }

    public ResultSet listar() {
        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM ");
        stringBuilder.append(convertaNomeClasse(classe.getName()));
        stringBuilder.append(";");
        return sessao.execute(stringBuilder.toString());
    }

    public Long obtenhaNovoId(String tipo_id) {
        StringBuilder stringBuilder = new StringBuilder("SELECT next_id FROM ids WHERE id_name = '");
        stringBuilder.append(tipo_id);
        stringBuilder.append("';");
        Long id = sessao.execute(stringBuilder.toString()).one().getLong("next_id");
        Long novoId = id+1;
        StringBuilder stringBuilderAtualizacao = new StringBuilder("UPDATE ids SET next_id = ");
        stringBuilderAtualizacao.append(novoId);
        stringBuilderAtualizacao.append(" WHERE id_name = '");
        stringBuilderAtualizacao.append(tipo_id);
        stringBuilderAtualizacao.append("'");
        stringBuilderAtualizacao.append(";");
        sessao.execute(stringBuilderAtualizacao.toString());
        return id;
    }

    private static String convertaNomeClasse(String classe) {
        String resultado = null;
//        System.out.println(classe);
        switch (classe) {
            case "br.com.nobreak.cjm.model.Aluno":
                resultado = "alunos";
                break;
            case "br.com.nobreak.cjm.model.Comentario":
                resultado = "comentarios";
                break;
            case "br.com.nobreak.cjm.model.Colaborador":
                resultado = "operadores";
                break;
            case "br.com.nobreak.cjm.model.Exercicio":
                resultado = "exercicios_busca";
                break;
            case "br.com.nobreak.cjm.model.Redacao":
                resultado = "redacoes";
                break;
            case "br.com.nobreak.cjm.model.ClassificacaoPadrao":
                resultado = "classificacao_padrao";
                break;
        }
        return resultado;
    }


}
