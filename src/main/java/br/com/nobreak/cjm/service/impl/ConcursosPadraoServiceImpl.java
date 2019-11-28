package br.com.nobreak.cjm.service.impl;

import br.com.nobreak.cjm.dao.ConcursosPadraoDao;
import br.com.nobreak.cjm.database_cassandra.Acesso;
import br.com.nobreak.cjm.database_cassandra.Conexao;
import br.com.nobreak.cjm.service.BancasPadraoService;
import com.datastax.driver.core.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ConcursosPadraoServiceImpl implements BancasPadraoService {

    private static final Logger log = LoggerFactory.getLogger(ColaboradorServiceImpl.class);

    Conexao conexao = new Conexao();
    Session sessao;
    ConcursosPadraoDao concursosPadraoDao;

    public ConcursosPadraoServiceImpl() {
        conexao.conecte();
        this.sessao = conexao.obterSessao();
        this.concursosPadraoDao = new ConcursosPadraoDao(sessao);
    }

    @Override
    public boolean registre(List<String> bancas) {
        Boolean resultado = false;
        try {
            resultado = concursosPadraoDao.registre(bancas);
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
            resultado = concursosPadraoDao.busque();
        } catch (Exception e) {
            log.info(e.toString());
//        } finally {
//            sessao.close();
        }
        return resultado;
    }
}
