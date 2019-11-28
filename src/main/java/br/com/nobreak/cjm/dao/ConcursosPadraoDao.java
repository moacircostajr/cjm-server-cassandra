package br.com.nobreak.cjm.dao;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.util.ArrayList;
import java.util.List;

public class ConcursosPadraoDao {

    Session sessao;

    public ConcursosPadraoDao(Session sessao) {
        this.sessao = sessao;
    }

    public boolean registre(List<String> concursos) {
        StringBuilder query = new StringBuilder("INSERT INTO concursos_padrao (id, concursos) VALUES ('1', [");
        Integer limite = concursos.size();
        for (int i = 0; i < limite; i++) {
            if (i < limite-1) {
                query.append("'");
                query.append(concursos.get(i));
                query.append("', ");
            } else {
                query.append("'");
                query.append(concursos.get(i));
                query.append("'");
            }
        }
        query.append("]);");
        return sessao.execute(query.toString()).wasApplied();
    }

    public List<String> busque() {
        List<String> concursos = new ArrayList<String>();
        StringBuilder query = new StringBuilder("SELECT id, concursos FROM concursos_padrao WHERE id = '1';");
        Row row = sessao.execute(query.toString()).one();
        Object[] objetos = row.getList("concursos", String.class).toArray();
        for (Object objeto:objetos
             ) {
            if (objeto instanceof String) {
                concursos.add(objeto.toString());
            }
        }
        return concursos;
    }

}
