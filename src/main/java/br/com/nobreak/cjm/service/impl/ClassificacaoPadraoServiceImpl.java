package br.com.nobreak.cjm.service.impl;

import br.com.nobreak.cjm.dao.ClassificacaoPadraoDao;
import br.com.nobreak.cjm.database_cassandra.Conexao;
import br.com.nobreak.cjm.service.ClassificacaoPadraoService;
import com.datastax.driver.core.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassificacaoPadraoServiceImpl implements ClassificacaoPadraoService {

    private static final Logger log = LoggerFactory.getLogger(ColaboradorServiceImpl.class);

    Conexao conexao = new Conexao();/*FIXME: conectar uma vez ao iniciar a aplicação*/
    Session sessao;
    ClassificacaoPadraoDao classificacaoPadraoDao;

    public ClassificacaoPadraoServiceImpl() {
        conexao.conecte();
        this.sessao = conexao.obterSessao();
        this.classificacaoPadraoDao = new ClassificacaoPadraoDao(sessao);
    }

    @Override
    public boolean registraClassificacao(String classificacao) {
        Boolean resultado = false;
        try {
            resultado = classificacaoPadraoDao.registre(classificacao);
        } catch (Exception e) {
            log.info(e.toString());
//        } finally {
//            sessao.close();
        }
        return resultado;
    }

    @Override
    public String buscaClassificacao() {
        String resultado = null;
        try {
            resultado = classificacaoPadraoDao.busque();
        } catch (Exception e) {
            log.info(e.toString());
//        } finally {
//            sessao.close();
        }
        return resultado;
    }
}
