package br.com.nobreak.cjm.dao;

import br.com.nobreak.cjm.database_cassandra.Conexao;
import br.com.nobreak.cjm.model.Redacao;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

import javax.servlet.http.Part;

public class RedacaoDaoTest {

    Conexao conexao = new Conexao();
    Session sessao;
    RedacaoDao redacaoDao;

    private UUID id;

    private final String EMAIL = "seila@seila.com.br";
    private final String TEMA = "Ciranda";
    private final Integer PONTUACAO = 10;

    private final String TEMA_ALT = "Moacir";
    private final Integer PONTUACAO_ALT = 10;

    @Before
    public void setUp() throws Exception {
        conexao.conecte();
        this.sessao = conexao.obterSessao();
        this.redacaoDao = new RedacaoDao(sessao);
    }

    @After
    public void tearDown() throws Exception {
        sessao.execute("BEGIN BATCH DELETE FROM redacoes WHERE id = " + id + ";" + "DELETE FROM redacoes_busca WHERE id = " + id + "; APPLY BATCH;");
        conexao.fecheConexao();
    }

    @Test
    public void testeRegistroBuscaAlteracaoListagens() throws IOException {
        Redacao redacao = new Redacao();
        redacao.setId(UUIDs.timeBased());
        redacao.setIdAluno(UUIDs.timeBased());
        redacao.setTema(TEMA);
        redacao.setPontuacao(PONTUACAO);
        redacao.setRedacaoOriginal(Util.prepareImagem("/home/moacir/Imagens/Captura de tela_2019-05-22_22-58-17.png"));
        redacao.setRedacaoCorrigida(Util.prepareImagem("/home/moacir/Imagens/Captura de tela_2019-05-22_22-58-48.png"));

        this.id = redacao.getId();

        Boolean resultado = redacaoDao.registre(redacao);

        assertTrue(resultado);

        Redacao redacaoBuscada = redacaoDao.busquePorId(this.id);

        assertEquals(id, redacaoBuscada.getId());
        assertEquals(TEMA, redacaoBuscada.getTema());
        assertEquals(PONTUACAO, redacaoBuscada.getPontuacao());
        assertEquals(redacao.getRedacaoCorrigida(), redacaoBuscada.getRedacaoCorrigida());
        assertEquals(redacao.getRedacaoCorrigida(), redacaoBuscada.getRedacaoCorrigida());

        redacaoBuscada.setTema(TEMA_ALT);
        redacaoBuscada.setPontuacao(PONTUACAO_ALT);
        redacaoBuscada.setRedacaoOriginal(Util.prepareImagem("/home/moacir/Imagens/Captura de tela_2019-05-22_23-00-39.png"));
        redacaoBuscada.setRedacaoCorrigida(Util.prepareImagem("/home/moacir/Imagens/Captura de tela_2019-05-22_23-01-36.png"));

        redacaoDao.registre(redacaoBuscada);

        Redacao redacaoAlterada = redacaoDao.busquePorId(redacaoBuscada.getId());

        assertEquals(TEMA_ALT,  redacaoAlterada.getTema());
        assertEquals(PONTUACAO_ALT, redacaoAlterada.getPontuacao());
        assertEquals(redacaoBuscada.getRedacaoOriginal(), redacaoAlterada.getRedacaoOriginal());
        assertEquals(redacaoBuscada.getRedacaoCorrigida(), redacaoAlterada.getRedacaoCorrigida());


    }

}
