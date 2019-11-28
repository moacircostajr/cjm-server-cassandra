package br.com.nobreak.cjm.service.impl;

import br.com.nobreak.cjm.dao.ExercicioDao;
import br.com.nobreak.cjm.database_cassandra.Conexao;
import br.com.nobreak.cjm.model.Exercicio;
import br.com.nobreak.cjm.service.ExercicioService;
import com.datastax.driver.core.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

//@Service
public class ExercicioServiceImpl implements ExercicioService {

    private static final Logger log = LoggerFactory.getLogger(ExercicioServiceImpl.class);

    Conexao conexao = new Conexao();
//    @Autowired
//    Conexao conexao;
    Session sessao;
    ExercicioDao exercicioDao;


    public ExercicioServiceImpl() {
        conexao.conecte();
        this.sessao = conexao.obterSessao();
        this.exercicioDao = new ExercicioDao(sessao);
    }

    @Override
    public Boolean registre(Exercicio exercicio) {
        Boolean resultado = false;
        try {
            resultado = exercicioDao.registre(exercicio);
        } catch (Exception e) {
//            log.info(e.toString());
            e.printStackTrace();
//        } finally {
//            sessao.close();
        }
        return resultado;
    }

    @Override
    public Exercicio busquePorId(UUID id) {
        Exercicio resultado = null;
        try {
            resultado = exercicioDao.busquePorId(id);
        } catch (Exception e) {
            log.info(e.toString());
//        } finally {
//            sessao.close();
        }
        return resultado;
    }

    @Override
    public List<Exercicio> listeCabecalhos() {
        List<Exercicio> resultado = null;
        try {
            resultado = exercicioDao.listaCabecalho();
        } catch (Exception e) {
            log.info(e.toString());
//        } finally {
//            sessao.close();
        }
        return resultado;
    }

    @Override
    public Exercicio busqueCabecalhoPorId(UUID id) {
        Exercicio resultado = null;
        try {
            resultado = exercicioDao.busqueCabecalhoPorId(id);
        } catch (Exception e) {
            log.info(e.toString());
//        } finally {
//            sessao.close();
        }
        return resultado;
    }
/*

    @Override
    public List<Exercicio> busqueCabecalhoPorMateria(String materia) {
        List<Exercicio> resultado = null;
        try {
            resultado = exercicioDao.busqueCabecalhoPorMateria(materia);
        } catch (Exception e) {
            log.info(e.toString());
//        } finally {
//            sessao.close();
        }
        return resultado;
    }

*/

/*

    @Override
    public List<Exercicio> busqueCabecalhoPorMateriaEBanca(String materia, String banca) {
        List<Exercicio> resultado = null;
        try {
            resultado = exercicioDao.busqueCabecalhoPorMateriaEBanca(materia, banca);
        } catch (Exception e) {
            log.info(e.toString());
//        } finally {
//            sessao.close();
        }
        return resultado;
    }

*/

/*

    @Override
    public List<Exercicio> busqueCabecalhoPorMateriaEBancaEProva(String materia, String banca, String prova) {
        List<Exercicio> resultado = null;
        try {
            resultado = exercicioDao.busqueCabecalhoPorMateriaEBancaEProva(materia, banca, prova);
        } catch (Exception e) {
            log.info(e.toString());
//        } finally {
//            sessao.close();
        }
        return resultado;
    }

*/


    @Override
    public List<Exercicio> busqueCabecalhoPorMateriaEBancaEProvaEAno(String materia, String banca, String prova, Integer ano) {
        List<Exercicio> resultado = null;
        try {
            resultado = exercicioDao.busqueCabecalhoPorMateriaEBancaEProvaEAno(materia, banca, prova, ano);
        } catch (Exception e) {
            log.info(e.toString());
//        } finally {
//            sessao.close();
        }
        return resultado;
    }

    @Override
    public List<UUID> busqueCabecalhoPorMateriaEBancaEProvaEAnoParaAluno(String materia, String banca, String prova, Integer ano) {
        List<UUID> resultado = null;
        try {
            resultado = exercicioDao.busqueCabecalhoParaAlunoPorMateriaEBancaEProvaEAno(materia, banca, prova, ano);
        } catch (Exception e) {
            log.info(e.toString());
//        } finally {
//            sessao.close();
        }
        return resultado;
    }

}
