package br.com.nobreak.cjm.service.impl;

import br.com.nobreak.cjm.dao.RedacaoDao;
import br.com.nobreak.cjm.database_cassandra.Conexao;
import br.com.nobreak.cjm.model.Redacao;
import br.com.nobreak.cjm.service.RedacaoService;
import com.datastax.driver.core.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

public class RedacaoServiceImpl implements RedacaoService {

    private static final Logger log = LoggerFactory.getLogger(RedacaoServiceImpl.class);

    Conexao conexao = new Conexao();/*FIXME: conectar uma vez ao iniciar a aplicação*/
    Session sessao;
    RedacaoDao redacaoDao;

    public RedacaoServiceImpl() {
        conexao.conecte();
        this.sessao = conexao.obterSessao();
        this.redacaoDao = new RedacaoDao(sessao);
    }


    @Override
    public Boolean registreRedacao(Redacao redacao) {
        Boolean resultado = false;
        try {
            resultado = redacaoDao.registre(redacao);
        } catch (Exception e) {
            log.info(e.toString());
//        } finally {
//            sessao.close();
        }
        return resultado;
    }

    @Override
    public Redacao busqueRedacaoPorId(UUID id) {
        Redacao resultado = null;
        try {
            resultado = redacaoDao.busquePorId(id);
        } catch (Exception e) {
            log.info(e.toString());
//        } finally {
//            sessao.close();
        }
        return resultado;
    }

    @Override
    public List<Redacao> busqueRedacoesPorIdAluno(UUID id) {
        List<Redacao> resultado = null;
        try {
            resultado = redacaoDao.busquePorIdAluno(id);
        } catch (Exception e) {
            log.info(e.toString());
//        } finally {
//            sessao.close();
        }
        return resultado;
    }

    @Override
    public List<Redacao> busqueRedacaoPorTema(String tema) {
        List<Redacao> resultado = null;
        try {
            resultado = redacaoDao.busquePorTema(tema);
        } catch (Exception e) {
            log.info(e.toString());
//        } finally {
//            sessao.close();
        }
        return resultado;
    }
}
