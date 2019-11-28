package br.com.nobreak.cjm.database_cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.Session;

public class Conexao {

    private Cluster cluster;
    private Session session;
    private String node = "127.0.0.1";
    private Integer port = 9042;
    public static final String KEYSPACE_NAME = "cjm";
    //private PoolingOptions poolingOptions = new PoolingOptions();

    public Conexao() {}

    public void conecte() {
        Cluster.Builder b = Cluster.builder().addContactPoint(node)/*.withPoolingOptions(poolingOptions)*/;
        if (port != null) {
            b.withPort(port);
        }
//        https://docs.datastax.com/en/developer/java-driver/3.6/manual/pooling/
        /*poolingOptions
                .setCoreConnectionsPerHost(HostDistance.LOCAL, 4)
                .setMaxConnectionsPerHost(HostDistance.LOCAL, 10)
                .setCoreConnectionsPerHost(HostDistance.REMOTE, 2)
                .setMaxConnectionsPerHost(HostDistance.REMOTE, 4);*/
        this.cluster = b.build();
        this.session = cluster.connect();
//        PoolingOptions poolingOptionsParaConsulta = cluster.getConfiguration().getPoolingOptions();
//        System.out.println(poolingOptionsParaConsulta.toString());
        this.session.execute("USE "+Conexao.KEYSPACE_NAME+";");
    }

    public Session obterSessao() {
        return this.session;
    }

    public void fecheConexao() {
        this.session.close(); /*usar isso no try/catch dos serviços*/
        this.cluster.close();
    }

}
