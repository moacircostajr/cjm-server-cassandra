package br.com.nobreak.cjm.dao;

import br.com.nobreak.cjm.model.Comentario;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ComentarioDao {


    Session sessao;
    DAO<Comentario> dao;

    public ComentarioDao(Session sessao) {
        this.sessao = sessao;
        this.dao = new DAO<Comentario>(Comentario.class, this.sessao);
    }

    public Comentario registre(Comentario comentario) {
        try {
            StringBuilder query = new StringBuilder();
            if (comentario.getId() == null) {
                Long idComentario = dao.obtenhaNovoId("comentario_id");
                comentario.setId(idComentario);
            }
            query.append("INSERT INTO comentarios (id, id_exercicio, email, comentario) VALUES (");
            query.append(comentario.getId());
            query.append(", ");
            query.append(comentario.getIdExercicio());
            query.append(", '");
            query.append(comentario.getEmail());
            query.append("', '");
            query.append(comentario.getComentario());
            query.append("');");

            /*query.append("UPDATE comentarios SET comentario = '");
            query.append(comentario.getComentario());
            query.append("' WHERE id = ");
            query.append(comentario.getId());
            query.append(";");*/

            sessao.execute(query.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return comentario;
    }

    public Comentario busquePorId(Long id) {
        return construaComentario(dao.buscarPorId(id.toString()));
    }

    public List<Comentario> busquePorEmail(String email) {
        return construaListaComentarios(dao.buscarPorEmail(email));
    }

    public List<Comentario> busquePorExercicio (UUID idExercicio) {
        List<Comentario> comentarios = null;
        StringBuilder query = new StringBuilder("SELECT * FROM comentarios WHERE id_exercicio = ");
        query.append(idExercicio);
        query.append(";");
        ResultSet resultado = sessao.execute(query.toString());
        comentarios = construaListaComentarios(resultado);
        return comentarios;
    }

    private List<Comentario> construaListaComentarios(ResultSet resultado) {
        ArrayList<Comentario> comentarios = new ArrayList<>();
        for (Row row:resultado) {
            comentarios.add(construaComentario(row));
        }
        return comentarios;
    }

    private Comentario construaComentario(Row resultado) {
        Comentario comentario = new Comentario();
        comentario.setId(resultado.getLong("id"));
        comentario.setIdExercicio(resultado.getUUID("id_exercicio"));
        comentario.setEmail(resultado.getString("email"));
        comentario.setComentario(resultado.getString("comentario"));
        return comentario;
    }
}
