package br.com.nobreak.cjm.dao;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class ClassificacaoPadraoDao {

    Session sessao;

    public ClassificacaoPadraoDao(Session sessao) {
        this.sessao = sessao;
    }

    public boolean registre(String classificacao) {
        StringBuilder query = new StringBuilder("INSERT INTO classificacao_padrao (id, classificacao) VALUES ('1', textAsBlob('");
        query.append(classificacao);
        query.append("'));");
        return sessao.execute(query.toString()).wasApplied();
    }

    public String busque() {
        StringBuilder query = new StringBuilder("SELECT id, blobAsText(classificacao) FROM classificacao_padrao WHERE id = '1';");
        Row row = sessao.execute(query.toString()).one();
        return row.getString("system.blobastext(classificacao)");
    }

}
