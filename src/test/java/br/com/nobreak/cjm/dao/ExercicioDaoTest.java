package br.com.nobreak.cjm.dao;

import br.com.nobreak.cjm.database_cassandra.Conexao;
import br.com.nobreak.cjm.model.Exercicio;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class ExercicioDaoTest {

    Conexao conexao = new Conexao();
    Session sessao;
    ExercicioDao exercicioDao;

    private UUID id;

    private final String ENUNCIADO1 = "Considere as seguintes alternativas:";
    private final String ENUNCIADO2 = "Assinale a afirmativa correta.";
    private final Integer GABARITO_OBJETIVO = 3;
    private final Integer PONTUACAO = 1;

    @Before
    public void setUp() throws Exception {
        conexao.conecte();
        this.sessao = conexao.obterSessao();
        this.exercicioDao = new ExercicioDao(sessao);
    }

    @After
    public void tearDown() throws Exception {
        sessao.execute("BEGIN BATCH DELETE FROM exercicios WHERE id = " + id + ";" + "DELETE FROM exercicios_busca WHERE id = " + id + "; APPLY BATCH;");
        conexao.fecheConexao();
    }

    @Test
    public void registre() {

        ArrayList<String> opcoes = new ArrayList<>();
        opcoes.add("casa branca");
        opcoes.add("casa azul");
        opcoes.add("casa rosa");
        opcoes.add("casa preta");/*resposta*/
        opcoes.add("casa laranja");

        ArrayList<String> materia = new ArrayList<>();
        materia.add("portugues");
        materia.add("gramatica");
        materia.add("morfologia");

        ArrayList<String> banca = new ArrayList<>();
        banca.add("CESPE");
        banca.add("FGV");
        banca.add("FCC");

        ArrayList<String> prova = new ArrayList<>();
        prova.add("ANALISTA JUDICIARIO");
        prova.add("JUIZ FEDERAL");
        prova.add("AUDITOR FISCAL");

        ArrayList<Integer> ano = new ArrayList<>();
        ano.add(2015);
        ano.add(2016);
        ano.add(2017);

        Exercicio exercicio = new Exercicio();
        exercicio.setId(UUIDs.timeBased());
        exercicio.setEnunciado1(ENUNCIADO1);
        exercicio.setImagemEnunciado(Util.prepareImagem("/home/moacir/Imagens/Captura de tela_2019-05-22_23-01-36.png"));
        exercicio.setEnunciado2(ENUNCIADO2);
        exercicio.setOpcoes(opcoes);
        exercicio.setGabaritoObjetivo(GABARITO_OBJETIVO);
        exercicio.setPontuacao(PONTUACAO);
        exercicio.setMateria(materia);
        exercicio.setBanca(banca);
        exercicio.setProva(prova);
        exercicio.setAno(ano);

        this.id = exercicio.getId();

        Boolean retorno = exercicioDao.registre(exercicio);
        assertTrue(retorno);

        Exercicio exercicioBuscado = exercicioDao.busquePorId(this.id);
        assertEquals(ENUNCIADO1, exercicioBuscado.getEnunciado1());
        assertEquals(GABARITO_OBJETIVO, exercicioBuscado.getGabaritoObjetivo());
        assertEquals(ENUNCIADO2, exercicioBuscado.getEnunciado2());

        System.out.println(exercicioBuscado.toString());

        Exercicio cabecalhoBuscado = exercicioDao.busqueCabecalhoPorId(id);
        assertEquals(this.id, cabecalhoBuscado.getId());

    }

}