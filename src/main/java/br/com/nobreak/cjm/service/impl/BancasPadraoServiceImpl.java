package br.com.nobreak.cjm.service.impl;

import br.com.nobreak.cjm.dao.BancasPadraoDao;
import br.com.nobreak.cjm.database_cassandra.Conexao;
import br.com.nobreak.cjm.service.BancasPadraoService;
import com.datastax.driver.core.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BancasPadraoServiceImpl implements BancasPadraoService {

    private static final Logger log = LoggerFactory.getLogger(ColaboradorServiceImpl.class);

    Conexao conexao = new Conexao();/*FIXME: conectar uma vez ao iniciar a aplicação*/
    Session sessao;
    BancasPadraoDao bancasPadraoDao;

    public BancasPadraoServiceImpl() {
        conexao.conecte();
        this.sessao = conexao.obterSessao();
        this.bancasPadraoDao = new BancasPadraoDao(sessao);
    }

    @Override
    public boolean registre(List<String> bancas) {
        Boolean resultado = false;
        try {
            resultado = bancasPadraoDao.registre(bancas);
        } catch (Exception e) {
            log.info(e.toString());
//        } finally {
//            sessao.close();
        }
        return resultado;
    }

    @Override
    public List<String> busque() {
        List<String> resultado = null;
        try {
            resultado = bancasPadraoDao.busque();
        } catch (Exception e) {
            log.info(e.toString());
//        } finally {
//            sessao.close();
        }
        return resultado;
    }
}
