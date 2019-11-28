package br.com.nobreak.cjm.dao;

import br.com.nobreak.cjm.database_cassandra.Conexao;
import br.com.nobreak.cjm.model.Aluno;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class AlunoDaoTest {

    Conexao conexao = new Conexao();
    Session sessao;
    AlunoDao alunoDao;

    private String id;

    private final String EMAIL_ALUNO = "moacircostajr8745394@gmail.com";
    private final String NOME_ALUNO = "Moacir Costa";
    private final String SENHA_ALUNO = "12345";
    private final String ENDERECO_ALUNO = "Rua dos Alfeneiros, 20";
    private final String BAIRRO_ALUNO = "Longsville";
    private final String CIDADE_ALUNO = "Sobral";
    private final String ESTADO_ALUNO = "CE";
    private final String TELEFONE_ALUNO = "(88)99755-6328";
    private final Integer PONTOS_ALUNO = 0;

    private final String EMAIL_ALT_ALUNO = "abcdefgij@klm.com";
    private final String NOME_ALT_ALUNO = "aeiou";
    private final String SENHA_ALT_ALUNO = "54321";
    private final String ENDERECO_ALT_ALUNO = "Rua do Tabaco, 02";
    private final String BAIRRO_ALT_ALUNO = "Seila";
    private final String CIDADE_ALT_ALUNO = "Amontada";
    private final String ESTADO_ALT_ALUNO = "AM";
    private final String TELEFONE_ALT_ALUNO = "(00)00000-0000";
    private final Integer PONTOS_ALT_ALUNO = 140;

    @Before
    public void setUp() throws Exception {
        conexao.conecte();
        this.sessao = conexao.obterSessao();
        this.alunoDao = new AlunoDao(sessao);
    }

    @After
    public void tearDown() throws Exception {
        sessao.execute("DELETE FROM alunos WHERE id = " + id + ";");
        conexao.fecheConexao();
    }

    @Test
    public void testeRegistroBuscaPorIdEEmailEAlteracao() {
        Aluno aluno = new Aluno();
        aluno.setId(UUIDs.timeBased());
        aluno.setEmail(EMAIL_ALUNO);
        aluno.setNome(NOME_ALUNO);
        aluno.setSenha(SENHA_ALUNO);
        aluno.setEndereco(ENDERECO_ALUNO);
        aluno.setBairro(BAIRRO_ALUNO);
        aluno.setCidade(CIDADE_ALUNO);
        aluno.setEstado(ESTADO_ALUNO);
        aluno.setTelefone(TELEFONE_ALUNO);
        aluno.setPontos(PONTOS_ALUNO);

        this.id = aluno.getId().toString();

        Boolean resultado = alunoDao.registre(aluno);
        assertTrue(resultado);

        Aluno alunoBuscadoporId = alunoDao.busquePorId(aluno.getId());
        assertEquals(EMAIL_ALUNO, alunoBuscadoporId.getEmail());
        assertEquals(SENHA_ALUNO, alunoBuscadoporId.getSenha());
        assertEquals(NOME_ALUNO, alunoBuscadoporId.getNome());
        assertEquals(ENDERECO_ALUNO, alunoBuscadoporId.getEndereco());
        assertEquals(CIDADE_ALUNO, alunoBuscadoporId.getCidade());
        assertEquals(BAIRRO_ALUNO, alunoBuscadoporId.getBairro());
        assertEquals(ESTADO_ALUNO, alunoBuscadoporId.getEstado());
        assertEquals(TELEFONE_ALUNO, alunoBuscadoporId.getTelefone());
        assertEquals(PONTOS_ALUNO, alunoBuscadoporId.getPontos());

        Aluno alunoBuscadoporEmail = alunoDao.busquePorEmail(EMAIL_ALUNO);
        assertEquals(EMAIL_ALUNO, alunoBuscadoporEmail.getEmail());
        assertEquals(SENHA_ALUNO, alunoBuscadoporEmail.getSenha());
        assertEquals(NOME_ALUNO, alunoBuscadoporEmail.getNome());
        assertEquals(ENDERECO_ALUNO, alunoBuscadoporEmail.getEndereco());
        assertEquals(CIDADE_ALUNO, alunoBuscadoporEmail.getCidade());
        assertEquals(BAIRRO_ALUNO, alunoBuscadoporEmail.getBairro());
        assertEquals(ESTADO_ALUNO, alunoBuscadoporEmail.getEstado());
        assertEquals(TELEFONE_ALUNO, alunoBuscadoporEmail.getTelefone());
        assertEquals(PONTOS_ALUNO, alunoBuscadoporEmail.getPontos());

        List<Aluno> alunos = alunoDao.listeAlunos();
        for (Aluno alunoListado:alunos
             ) {
            System.out.println("");
            System.out.println("LISTAGEM DE ALUNOS");
            System.out.println(alunoListado.toString());
        }

        List<Aluno> alunoBuscadoPorNome = alunoDao.busquePorNome("moacir");
        for (Aluno alunoBuscaPorNome:alunoBuscadoPorNome
        ) {
            System.out.println("");
            System.out.println("ALUNO BUSCADO POR NOME");
            System.out.println(alunoBuscaPorNome.toString());
        }


        alunoBuscadoporId.setEmail(EMAIL_ALT_ALUNO);
        alunoBuscadoporId.setSenha(SENHA_ALT_ALUNO);
        alunoBuscadoporId.setNome(NOME_ALT_ALUNO);
        alunoBuscadoporId.setEndereco(ENDERECO_ALT_ALUNO);
        alunoBuscadoporId.setCidade(CIDADE_ALT_ALUNO);
        alunoBuscadoporId.setBairro(BAIRRO_ALT_ALUNO);
        alunoBuscadoporId.setEstado(ESTADO_ALT_ALUNO);
        alunoBuscadoporId.setTelefone(TELEFONE_ALT_ALUNO);
        alunoBuscadoporId.setPontos(PONTOS_ALT_ALUNO);

        Boolean registro = alunoDao.registre(alunoBuscadoporId);
        assertTrue(registro);

        Aluno alunoAlterado = alunoDao.busquePorId(alunoBuscadoporId.getId());

        assertEquals(EMAIL_ALT_ALUNO, alunoAlterado.getEmail());
        assertEquals(SENHA_ALT_ALUNO, alunoAlterado.getSenha());
        assertEquals(NOME_ALT_ALUNO, alunoAlterado.getNome());
        assertEquals(ENDERECO_ALT_ALUNO, alunoAlterado.getEndereco());
        assertEquals(CIDADE_ALT_ALUNO, alunoAlterado.getCidade());
        assertEquals(BAIRRO_ALT_ALUNO, alunoAlterado.getBairro());
        assertEquals(ESTADO_ALT_ALUNO, alunoAlterado.getEstado());
        assertEquals(TELEFONE_ALT_ALUNO, alunoAlterado.getTelefone());
        assertEquals(PONTOS_ALT_ALUNO, alunoAlterado.getPontos());

    }

}
