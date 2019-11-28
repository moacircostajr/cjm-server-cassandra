package br.com.nobreak.cjm.service.impl;

import br.com.nobreak.cjm.dao.ComentarioDao;
import br.com.nobreak.cjm.database_cassandra.Conexao;
import br.com.nobreak.cjm.model.Comentario;
import br.com.nobreak.cjm.service.ComentarioService;
import com.datastax.driver.core.Session;

import java.util.List;
import java.util.UUID;

public class ComentarioServiceImpl implements ComentarioService {

    Conexao conexao = new Conexao();/*FIXME: conectar uma vez ao iniciar a aplicação*/
    Session sessao;
    ComentarioDao comentarioDao;

    public ComentarioServiceImpl() {
        conexao.conecte();
        this.sessao = conexao.obterSessao();
        this.comentarioDao = new ComentarioDao(sessao);
    }


    @Override
    public Comentario registre(Comentario comentario) {
        Comentario resultado = null;
        try {
            resultado = comentarioDao.registre(comentario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            sessao.close();
        }
        return resultado;
    }

    @Override
    public Comentario busquePorId(Long id) {
        Comentario resultado = null;
        try {
            resultado = comentarioDao.busquePorId(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            sessao.close();
        }
        return resultado;
    }

    @Override
    public List<Comentario> busquePorEmail(String email) {
        List<Comentario> resultado = null;
        try {
            resultado = comentarioDao.busquePorEmail(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            sessao.close();
        }
        return resultado;
    }
}
