package br.com.nobreak.cjm.service.impl;

import br.com.nobreak.cjm.dao.AlunoDao;
import br.com.nobreak.cjm.database_cassandra.Conexao;
import br.com.nobreak.cjm.model.Aluno;
import br.com.nobreak.cjm.service.AlunoService;
import com.datastax.driver.core.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

public class AlunoServiceImpl implements AlunoService {

    private static final Logger log = LoggerFactory.getLogger(AlunoServiceImpl.class);

    Conexao conexao = new Conexao();
    Session sessao;
    AlunoDao alunoDao;

    public AlunoServiceImpl() {
        conexao.conecte();
        this.sessao = conexao.obterSessao();
        this.alunoDao = new AlunoDao(sessao);
    }

    @Override
    public Boolean registreAluno(Aluno aluno) {
        Boolean resultado = false;
        try {
            resultado = alunoDao.registre(aluno);
        } catch (Exception e) {
            log.info(e.toString());
//        } finally {
//            sessao.close();
        }
        return resultado;
    }

    @Override
    public Boolean atualizeAluno(Aluno aluno) {
        Boolean resultado = false;
        try {
            resultado = alunoDao.atualizeAluno(aluno);
        } catch (Exception ex) {
            log.info(ex.toString());
//        } finally {
//            sessao.close();
        }
        return resultado;
    }

    @Override
    public Boolean atualizeEstatística(Aluno aluno) {
        Boolean resultado = false;
        try {
            resultado = alunoDao.atualizeEstatistica(aluno);
        } catch (Exception ex) {
            log.info(ex.toString());
//        } finally {
//            sessao.close();
        }
        return resultado;
    }


    @Override
    public Aluno busqueAlunoPorId(UUID id) {
        Aluno aluno = null;
        try {
            aluno = alunoDao.busquePorId(id);
        } catch(Exception e) {
            log.info(e.toString());
//        } finally {
//            sessao.close();
        }
        return aluno;
    }

    @Override
    public Aluno busqueAlunoPorEmail(String email) {
        Aluno aluno = null;
        try {
            aluno = alunoDao.busquePorEmail(email);
        } catch(Exception e) {
            log.info(e.toString());
//        } finally {
//            sessao.close();
        }
        return aluno;
    }

    @Override
    public List<Aluno> busqueAlunoPorNome(String nome) {
        List<Aluno> alunos = null;
        try {
            alunos = alunoDao.busquePorNome(nome);
        } catch (Exception e) {
            log.info(e.toString());
//        } finally {
//            sessao.close();
        }
        return alunos;
    }

    @Override
    public List<Aluno> listeTodosAlunos() {
        List<Aluno> alunos = null;
        try {
            alunos = alunoDao.listeAlunos();
        } catch (Exception e) {
            log.info(e.toString());
//        } finally {
//            sessao.close();
        }
        return alunos;
    }

    public Aluno verifiqueCredenciaisAluno(String email, String senha) {
        try {
            Aluno aluno = busqueAlunoPorEmail(email);
            if (aluno.getSenha().equals(senha)) {
                return aluno;
            } else {
                return null;
            }
        } catch (Exception e) {
            log.info(e.toString());
            return null;
//        } finally {
//            sessao.close();
        }
    }
}
