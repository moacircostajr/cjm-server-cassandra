package br.com.nobreak.cjm.database_cassandra;

import com.datastax.driver.core.Session;

public abstract class Acesso {

    Conexao conexao = new Conexao();

    public Session acesseDB() {
        conexao.conecte();
        return conexao.obterSessao();

    }

    public Session useSessao() {
        return conexao.obterSessao();
    }

    public void fecheDB() {
        conexao.fecheConexao();
    }

}
