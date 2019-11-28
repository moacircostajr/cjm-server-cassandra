package br.com.nobreak.cjm.dao;


import br.com.nobreak.cjm.model.Colaborador;
import br.com.nobreak.cjm.model.Perfil;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ColaboradorDao {

    Session sessao;
    DAO<Colaborador> dao;

    public ColaboradorDao(Session sessao) {
        this.sessao = sessao;
        this.dao = new DAO<Colaborador>(Colaborador.class, this.sessao);
    }

    public Boolean registre(Colaborador colaborador) {
        StringBuilder query = new StringBuilder("INSERT INTO operadores (id, email, senha, nome, telefone, perfil) VALUES (");
        query.append(colaborador.getId().toString());
        query.append(", '");
        query.append(colaborador.getEmail());
        query.append("', '");
        query.append(colaborador.getSenha());
        query.append("', '");
        query.append(colaborador.getNome());
        query.append("', '");
        query.append(colaborador.getTelefone());
        query.append("', '");
        query.append(colaborador.getPerfil());
        query.append("');");
        return sessao.execute(query.toString()).wasApplied();
    }

    public Colaborador busquePorEmail(String email) {
        Colaborador resultado = null;
        List<Colaborador> operadores = construaListaOperadores(dao.buscarPorEmail(email));
        if (operadores.size() > 1) {
            return null;
        } else {
            for (Colaborador colaborador :operadores
                 ) {
                resultado = colaborador;
            }
            return resultado;
        }
    }

    public Colaborador busquePorId(UUID id) {
        return construaOperador(dao.buscarPorId(id.toString()));
    }

    public List<Colaborador> listeOperadores() {
        return construaListaOperadores(dao.listar());
    }

    public List<Colaborador> busquePorNome(String nome) {
        List<Colaborador> operadores = null;
        try {
            operadores = construaListaOperadores(dao.buscarPorNome(nome));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return operadores;
    }

    private List<Colaborador> construaListaOperadores(ResultSet resultado) {
        ArrayList<Colaborador> operadores = new ArrayList<>();
        for (Row row:resultado) {
            operadores.add(construaOperador(row));
        }
        return operadores;
    }

    private Colaborador construaOperador(Row resultado) {
        Colaborador colaborador = new Colaborador();
        colaborador.setId(resultado.getUUID("id"));
        colaborador.setEmail(resultado.getString("email"));
        colaborador.setSenha(resultado.getString("senha"));
        colaborador.setNome(resultado.getString("nome"));
        colaborador.setTelefone(resultado.getString("telefone"));
        colaborador.setPerfil(facaAjusteDePerfil(resultado.getString("perfil")));
        return colaborador;
    }

    private static Perfil facaAjusteDePerfil(String perfil) {
        Perfil result = null;
        switch (perfil) {
            case "ADMIN":
                result = Perfil.ADMIN;
                break;
            case "OPERADOR":
                result = Perfil.OPERADOR;
                break;
            case "PROFESSOR":
                result = Perfil.PROFESSOR;
                break;
        }
        return result;
    }

}
