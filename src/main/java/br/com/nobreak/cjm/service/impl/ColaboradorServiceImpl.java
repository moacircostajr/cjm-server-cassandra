package br.com.nobreak.cjm.service.impl;

import br.com.nobreak.cjm.dao.ColaboradorDao;
import br.com.nobreak.cjm.database_cassandra.Conexao;
import br.com.nobreak.cjm.model.Colaborador;
import br.com.nobreak.cjm.service.ColaboradorService;
import com.datastax.driver.core.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

/*TODO: fazer log de erro interno do sistema*/
public class ColaboradorServiceImpl implements ColaboradorService {

    private static final Logger log = LoggerFactory.getLogger(ColaboradorServiceImpl.class);

    Conexao conexao = new Conexao();
    Session sessao;
    ColaboradorDao colaboradorDao;

    public ColaboradorServiceImpl() {
        conexao.conecte();
        this.sessao = conexao.obterSessao();
        this.colaboradorDao = new ColaboradorDao(sessao);
    }


    @Override
    public Boolean registreOperador(Colaborador colaborador) {
        Boolean resultado = false;
        try {
            resultado = colaboradorDao.registre(colaborador);
        } catch (Exception e) {
            log.info(e.toString());
//            e.printStackTrace();
//        } finally {
//            sessao.close();
        }
        return resultado;
    }

    @Override
    public Colaborador busqueOperadorPorEmail(String email) {
        Colaborador colaborador = null;
        try {
            colaborador = colaboradorDao.busquePorEmail(email);
        } catch(Exception e) {
            log.info(e.toString());
//        } finally {
//            sessao.close();
        }
        return colaborador;
    }

    @Override
    public Colaborador busqueOperadorPorId(UUID id) {
        Colaborador colaborador = null;
        try {
            colaborador = colaboradorDao.busquePorId(id);
        } catch(Exception e) {
            log.info(e.toString());
//        } finally {
//            sessao.close();
        }
        return colaborador;
    }

    @Override
    public List<Colaborador> busqueOperadorPorNome(String nome) {
        List<Colaborador> colaboradors = null;
        try {
            colaboradors = colaboradorDao.busquePorNome(nome);
        } catch (Exception e) {
            log.info(e.toString());
//        } finally {
//            sessao.close();
        }
        return colaboradors;
    }

    @Override
    public List<Colaborador> listeTodosOperadores() {
        List<Colaborador> colaboradors = null;
        try {
            colaboradors = colaboradorDao.listeOperadores();
        } catch (Exception e) {
            log.info(e.toString());
//        } finally {
//            sessao.close();
        }
        return colaboradors;
    }

    public Colaborador verifiqueCredenciaisOperador(String email, String senhaE) {
        Colaborador colaborador = null;
        try {
            Colaborador colaboradorT = busqueOperadorPorEmail(email);
            if (colaboradorT.getSenha().equals(senhaE)) {
                colaborador = colaboradorT;
            } else {
                return null;
            }
        } catch (Exception e) {
            log.info(e.toString());
//        } finally {
//            sessao.close();
        }
        return colaborador;
    }
}
