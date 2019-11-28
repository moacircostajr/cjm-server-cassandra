package br.com.nobreak.cjm.dao;

import br.com.nobreak.cjm.database_cassandra.Conexao;
import br.com.nobreak.cjm.model.Colaborador;
import br.com.nobreak.cjm.model.Perfil;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ColaboradorDaoTest {

    Conexao conexao = new Conexao();
    Session sessao;
    ColaboradorDao colaboradorDao;

    private String id;

    private final String EMAIL_OPERADOR = "moacircostajr@gmail.com";
    private final String SENHA_OPERADOR = "12345";
    private final String NOME_OPERADOR = "Moacir Costa";
    private final String TELEFONE_OPERADOR = "(88)99755-6328";
    private final Perfil PERFIL_OPERADOR = Perfil.ADMIN;

    private final String EMAIL_ALT_OPERADOR = "seila@seila.com";
    private final String SENHA_ALT_OPERADOR = "456789";
    private final String NOME_ALT_OPERADOR = "ABCDEFFG";
    private final String TELEFONE_ALT_OPERADOR = "(99)99999-9999";
    private final Perfil PERFIL_ALT_OPERADOR = Perfil.PROFESSOR;

    @Before
    public void setUp() throws Exception {
        conexao.conecte();
        this.sessao = conexao.obterSessao();
        this.colaboradorDao = new ColaboradorDao(this.sessao);
    }

    @After
    public void tearDown() throws Exception {
        sessao.execute("DELETE FROM operadores WHERE id = " + id + ";");
        conexao.fecheConexao();
    }

    @Test
    public void testeRegistroEBuscaPorIdEEmail() {
        Colaborador colaborador = new Colaborador();
        colaborador.setId(UUIDs.timeBased());
        colaborador.setEmail(EMAIL_OPERADOR);
        colaborador.setSenha(SENHA_OPERADOR);
        colaborador.setNome(NOME_OPERADOR);
        colaborador.setTelefone(TELEFONE_OPERADOR);
        colaborador.setPerfil(PERFIL_OPERADOR);

        this.id = colaborador.getId().toString();

        Boolean resultado = colaboradorDao.registre(colaborador);

        assertTrue(resultado);

        Colaborador colaboradorBuscadoporId = colaboradorDao.busquePorId(colaborador.getId());
        assertEquals(EMAIL_OPERADOR, colaboradorBuscadoporId.getEmail());
        assertEquals(SENHA_OPERADOR, colaboradorBuscadoporId.getSenha());
        assertEquals(NOME_OPERADOR, colaboradorBuscadoporId.getNome());
        assertEquals(TELEFONE_OPERADOR, colaboradorBuscadoporId.getTelefone());
        assertEquals(PERFIL_OPERADOR, colaboradorBuscadoporId.getPerfil());

        Colaborador colaboradorBuscadoPorEmail = colaboradorDao.busquePorEmail(colaborador.getEmail());
        assertEquals(EMAIL_OPERADOR, colaboradorBuscadoPorEmail.getEmail());
        assertEquals(SENHA_OPERADOR, colaboradorBuscadoPorEmail.getSenha());
        assertEquals(NOME_OPERADOR, colaboradorBuscadoPorEmail.getNome());
        assertEquals(TELEFONE_OPERADOR, colaboradorBuscadoPorEmail.getTelefone());
        assertEquals(PERFIL_OPERADOR, colaboradorBuscadoPorEmail.getPerfil());

        List<Colaborador> operadores = colaboradorDao.listeOperadores();
        for (Colaborador colaboradorListado :operadores
        ) {
            System.out.println("");
            System.out.println("LISTAGEM DE OPERADORS");
            System.out.println(colaboradorListado.toString());
        }

        List<Colaborador> colaboradorBuscadoPorNome = colaboradorDao.busquePorNome("moacir");
        for (Colaborador colaboradorBuscaPorNome : colaboradorBuscadoPorNome
        ) {
            System.out.println("");
            System.out.println("OPERADOR BUSCADO POR NOME");
            System.out.println(colaboradorBuscaPorNome.toString());
        }

        colaboradorBuscadoporId.setEmail(EMAIL_ALT_OPERADOR);
        colaboradorBuscadoporId.setSenha(SENHA_ALT_OPERADOR);
        colaboradorBuscadoporId.setNome(NOME_ALT_OPERADOR);
        colaboradorBuscadoporId.setTelefone(TELEFONE_ALT_OPERADOR);
        colaboradorBuscadoporId.setPerfil(PERFIL_ALT_OPERADOR);

        Boolean registro = colaboradorDao.registre(colaboradorBuscadoporId);

        assertTrue(registro);

        Colaborador colaboradorAlterado = colaboradorDao.busquePorId(colaboradorBuscadoporId.getId());

        assertEquals(EMAIL_ALT_OPERADOR, colaboradorAlterado.getEmail());
        assertEquals(SENHA_ALT_OPERADOR, colaboradorAlterado.getSenha());
        assertEquals(NOME_ALT_OPERADOR, colaboradorAlterado.getNome());
        assertEquals(TELEFONE_ALT_OPERADOR, colaboradorAlterado.getTelefone());
        assertEquals(PERFIL_ALT_OPERADOR, colaboradorAlterado.getPerfil());

    }
}