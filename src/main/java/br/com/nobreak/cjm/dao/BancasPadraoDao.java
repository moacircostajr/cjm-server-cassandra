package br.com.nobreak.cjm.dao;

import br.com.nobreak.cjm.model.BancasPadrao;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.util.ArrayList;
import java.util.List;

public class BancasPadraoDao {

    Session sessao;

    public BancasPadraoDao(Session sessao) {
        this.sessao = sessao;
    }

    public boolean registre(List<String> bancas) {
        StringBuilder query = new StringBuilder("INSERT INTO bancas_padrao (id, bancas) VALUES ('1', [");
        Integer limite = bancas.size();
        for (int i = 0; i < limite; i++) {
            if (i < limite-1) {
                query.append("'");
                query.append(bancas.get(i));
                query.append("', ");
            } else {
                query.append("'");
                query.append(bancas.get(i));
                query.append("'");
            }
        }
        query.append("]);");
        return sessao.execute(query.toString()).wasApplied();
    }

    public List<String> busque() {
        List<String> bancas = new ArrayList<String>();
        StringBuilder query = new StringBuilder("SELECT id, bancas FROM bancas_padrao WHERE id = '1';");
        Row row = sessao.execute(query.toString()).one();
        Object[] objetos = row.getList("bancas", String.class).toArray();
        for (Object objeto:objetos
             ) {
            if (objeto instanceof String) {
                bancas.add(objeto.toString());
            }
        }
        return bancas;
    }

}
