package br.com.nobreak.cjm.dao;

import br.com.nobreak.cjm.database_cassandra.Conexao;
import br.com.nobreak.cjm.model.Comentario;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class ComentarioDaoTest {

    Conexao conexao = new Conexao();
    Session sessao;
    ComentarioDao comentarioDao;

    private Long id;

    private final String EMAIL = "jessikabarros777@gmail.com";
    private final String COMENTARIO = "bla bla bla bla bla bla bla bla bla bla bla";

    private final String COMENTARIO_ALT = "lllllllllllllllllllllllllll";

    @Before
    public void setUp() throws Exception {
        conexao.conecte();
        this.sessao = conexao.obterSessao();
        this.comentarioDao = new ComentarioDao(sessao);
    }

    @After
    public void tearDown() throws Exception {
        sessao.execute("DELETE FROM comentarios WHERE id = " + id + ";" );
        conexao.fecheConexao();
    }

    @Test
    public void testeRegistroBuscaAlteracao() {

        Comentario comentario = new Comentario();
        comentario.setIdExercicio(UUIDs.timeBased());
        comentario.setEmail(EMAIL);
        comentario.setComentario(COMENTARIO);


        Comentario resultado = comentarioDao.registre(comentario);

        assertNotNull(resultado.getId());
        this.id = resultado.getId();

        Comentario comentarioBuscadoPorId = comentarioDao.busquePorId(this.id);

        assertEquals(EMAIL, comentarioBuscadoPorId.getEmail());
        assertEquals(COMENTARIO, comentarioBuscadoPorId.getComentario());

        comentarioBuscadoPorId.setComentario(COMENTARIO_ALT);

        comentarioDao.registre(comentarioBuscadoPorId);

        Comentario verificaAlteracao = comentarioDao.busquePorId(comentarioBuscadoPorId.getId());
        assertEquals(COMENTARIO_ALT, verificaAlteracao.getComentario());


    }
}