package br.com.nobreak.cjm.dao;

import br.com.nobreak.cjm.model.Exercicio;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExercicioDao {

    Session sessao;
    DAO<Exercicio> dao;


    public ExercicioDao(Session sessao) {
        this.sessao = sessao;
        this.dao = new DAO<Exercicio>(Exercicio.class, this.sessao);
    }
    /*TODO: atualizacao*/
    public Boolean registre(Exercicio exercicio) {
//        System.out.println(exercicio.toString());
        UUID exercicioId = exercicio.getId();
        String enunciado1 = exercicio.getEnunciado1();
        String enunciado2 = exercicio.getEnunciado2();
        Integer pontuacao = exercicio.getPontuacao();
        StringBuilder queryBusca = new StringBuilder("INSERT INTO exercicios_busca (id, enunciado1, enunciado2, pontuacao, materia, banca, prova, ano) VALUES (");
        queryBusca.append(exercicioId).append(", '");
        queryBusca.append(enunciado1).append("', '");
        queryBusca.append(enunciado2).append("', ");
        queryBusca.append(pontuacao).append(", [");

        StringBuilder stringMateria = new StringBuilder();
        int tMateria = exercicio.getMateria().size();
        for(int h = 0; h < tMateria; h++) {
            if (h < tMateria-1) {
                stringMateria.append("'");
                stringMateria.append(exercicio.getMateria().get(h));
                stringMateria.append("', ");
            } else {
                stringMateria.append("'");
                stringMateria.append(exercicio.getMateria().get(h));
                stringMateria.append("'");
            }
        }
        queryBusca.append(stringMateria.toString());

        queryBusca.append("], [");


        StringBuilder stringBanca = new StringBuilder();
        int tBanca = exercicio.getBanca().size();
        for(int j = 0; j < tBanca; j++) {
            if (j < tBanca-1) {
                stringBanca.append("'");
                stringBanca.append(exercicio.getBanca().get(j));
                stringBanca.append("', ");
            } else {
                stringBanca.append("'");
                stringBanca.append(exercicio.getBanca().get(j));
                stringBanca.append("'");
            }
        }
        queryBusca.append(stringBanca.toString());

        queryBusca.append("], [");

        StringBuilder stringProva = new StringBuilder();
        int tProva = exercicio.getBanca().size();
        for(int p = 0; p < tProva; p++) {
            if (p < tProva-1) {
                stringProva.append("'");
                stringProva.append(exercicio.getProva().get(p));
                stringProva.append("', ");
            } else {
                stringProva.append("'");
                stringProva.append(exercicio.getProva().get(p));
                stringProva.append("'");
            }
        }
        queryBusca.append(stringProva.toString());

        queryBusca.append("], [");


        StringBuilder stringAno = new StringBuilder();
        int tAno = exercicio.getAno().size();
        for(int a = 0; a < tAno; a++) {
            if (a < tAno-1) {
                stringAno.append(exercicio.getAno().get(a));
                stringAno.append(", ");
            } else {
                stringAno.append(exercicio.getAno().get(a));
            }
        }
        queryBusca.append(stringAno.toString());

        queryBusca.append("]);");

        StringBuilder queryExercicio = new StringBuilder("INSERT INTO exercicios (id, enunciado1, imagem_enunciado, enunciado2, opcoes, gabarito_objetivo, gabarito_subjetivo, pontuacao) VALUES (");
        queryExercicio.append(exercicioId);
        queryExercicio.append(", '");
        queryExercicio.append(enunciado1);
        queryExercicio.append("', textAsBlob('");
        queryExercicio.append(exercicio.getImagemEnunciado());
        queryExercicio.append("'), '");
        queryExercicio.append(enunciado2);
        queryExercicio.append("', [");
        int tOpcoes = exercicio.getOpcoes().size();
        for (int i = 0; i < tOpcoes; i++) {
            if (i < tOpcoes-1) {
                queryExercicio.append(" '");
                queryExercicio.append(exercicio.getOpcoes().get(i));
                queryExercicio.append("', ");
            } else {
                queryExercicio.append("'");
                queryExercicio.append(exercicio.getOpcoes().get(i));
                queryExercicio.append("'");
            }
        }
        queryExercicio.append("], ");
        queryExercicio.append(exercicio.getGabaritoObjetivo());
        queryExercicio.append(", '");
        queryExercicio.append(exercicio.getGabaritoSubjetivo());
        queryExercicio.append("', ");
        queryExercicio.append(pontuacao);
        queryExercicio.append(");");
//        System.out.println(queryBusca.toString());
//        System.out.println(queryExercicio.toString());
        try {
            sessao.execute(queryBusca.toString());
            sessao.execute(queryExercicio.toString()); /*FIXME: verificar erro nessa query*/
            return true;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    public List<Exercicio> listaCabecalho() {
        ResultSet resultSet = this.dao.listar();
        return construaListaCabecalho(resultSet);
    }

    public Exercicio busqueCabecalhoPorId(UUID id) {
        StringBuilder query = new StringBuilder("SELECT id, enunciado1, enunciado2, pontuacao FROM exercicios_busca WHERE id=");
        query.append(id);
        query.append(";");
        return construaCabecalho(sessao.execute(query.toString()).one());
    }

/*

    public List<Exercicio> busqueCabecalhoPorMateria(String materia) {
        StringBuilder query = new StringBuilder("SELECT id, enunciado1, enunciado2, pontuacao FROM exercicios_busca WHERE materia CONTAINS '");
        query.append(materia);
        query.append("';");
        ResultSet resultSet = sessao.execute(query.toString());
        return construaListaCabecalho(resultSet);
    }

    public List<Exercicio> busqueCabecalhoPorMateriaEBanca(String materia, String banca) {
        StringBuilder query = new StringBuilder("SELECT id, enunciado1, enunciado2, pontuacao FROM exercicios_busca WHERE materia CONTAINS '");
        query.append(materia);
        query.append("' AND banca CONTAINS '");
        query.append(banca);
        query.append("';");
        ResultSet resultSet = sessao.execute(query.toString());
        return construaListaCabecalho(resultSet);
    }

    public List<Exercicio> busqueCabecalhoPorMateriaEBancaEProva(String materia, String banca, String prova) {
        StringBuilder query = new StringBuilder("SELECT id, enunciado1, enunciado2, pontuacao FROM exercicios_busca WHERE materia CONTAINS '");
        query.append(materia);
        query.append("' AND banca CONTAINS '");
        query.append(banca);
        query.append("' AND prova CONTAINS '");
        query.append(prova);
        query.append("';");
        ResultSet resultSet = sessao.execute(query.toString());
        return construaListaCabecalho(resultSet);
    }

*/

    public List<Exercicio> busqueCabecalhoPorMateriaEBancaEProvaEAno(String materia, String banca, String prova, Integer ano) {
        StringBuilder query = new StringBuilder("SELECT id, enunciado1, enunciado2, pontuacao FROM exercicios_busca WHERE materia CONTAINS '");
        query.append(materia);
        query.append("' AND banca CONTAINS '");
        query.append(banca);
        query.append("' AND prova CONTAINS '");
        query.append(prova);
        query.append("' AND ano CONTAINS ");
        query.append(ano);
        query.append(" ALLOW FILTERING;");
        ResultSet resultSet = sessao.execute(query.toString());
        return construaListaCabecalho(resultSet);
    }

    public List<UUID> busqueCabecalhoParaAlunoPorMateriaEBancaEProvaEAno(String materia, String banca, String prova, Integer ano) {
        StringBuilder query = new StringBuilder("SELECT id FROM exercicios_busca WHERE materia CONTAINS '");
        query.append(materia);
        query.append("' AND banca CONTAINS '");
        query.append(banca);
        query.append("' AND prova CONTAINS '");
        query.append(prova);
        query.append("' AND ano CONTAINS ");
        query.append(ano);
        query.append(" ALLOW FILTERING;");
        ResultSet resultSet = sessao.execute(query.toString());
        return construaListaCabecalhoParaAluno(resultSet);
    }

    private List<Exercicio> construaListaCabecalho(ResultSet resultado) {
        ArrayList<Exercicio> exercicios = new ArrayList<>();
        for (Row row:resultado) {
            exercicios.add(construaCabecalho(row));
        }
        return exercicios;
    }

    private List<UUID> construaListaCabecalhoParaAluno(ResultSet resultado) {
        ArrayList<UUID> exercicios = new ArrayList<>();
        for (Row row:resultado) {
            exercicios.add(row.getUUID("id"));
        }
        return exercicios;
    }

    private Exercicio construaCabecalho(Row row) {
        Exercicio exercicio = new Exercicio();
        exercicio.setId(row.getUUID("id"));
        exercicio.setEnunciado1(row.getString("enunciado1"));
        exercicio.setEnunciado2(row.getString("enunciado2"));
        exercicio.setPontuacao(row.getInt("pontuacao"));
        return exercicio;
    }



    public Exercicio busquePorId(UUID id) {
        StringBuilder query = new StringBuilder("SELECT id, enunciado1, blobAsText(imagem_enunciado), enunciado2, opcoes, gabarito_objetivo, gabarito_subjetivo, pontuacao FROM exercicios WHERE id=");
        query.append(id);
        query.append(";");
        return construaExercicio(sessao.execute(query.toString()).one());
    }

    private Exercicio construaExercicio(Row resultado) {
        Exercicio exercicio = new Exercicio();
        ArrayList<String> opcoes = new ArrayList<>();
        ArrayList<String> materia = new ArrayList<>();
        ArrayList<String> banca = new ArrayList<>();
        ArrayList<String> prova = new ArrayList<>();
        ArrayList<Integer> ano = new ArrayList<>();
        UUID idExercicioTmp = resultado.getUUID("id");
        exercicio.setId(idExercicioTmp);
        exercicio.setEnunciado1(resultado.getString("enunciado1"));
        exercicio.setImagemEnunciado(resultado.getString("system.blobastext(imagem_enunciado)"));
        exercicio.setEnunciado2(resultado.getString("enunciado2"));

        Object[] objetosOpcoes = resultado.getList("opcoes", String.class).toArray();
        for (Object objeto:objetosOpcoes
             ) {
            if (objeto instanceof String) {
                opcoes.add(objeto.toString());
            }
        }
        exercicio.setOpcoes(opcoes);

        exercicio.setGabaritoObjetivo(resultado.getInt("gabarito_objetivo"));
        exercicio.setGabaritoSubjetivo(resultado.getString("gabarito_subjetivo"));
        exercicio.setPontuacao(resultado.getInt("pontuacao"));

        StringBuilder queryBuscaCategoria = new StringBuilder("SELECT * FROM exercicios_busca WHERE id=");
        queryBuscaCategoria.append(idExercicioTmp);
        queryBuscaCategoria.append(";");
        Row row = sessao.execute(queryBuscaCategoria.toString()).one();

        Object[] objetosMateria = row.getList("materia", String.class).toArray();
        for (Object objeto:objetosMateria
        ) {
            if (objeto instanceof String) {
                materia.add(objeto.toString());
            }
        }
        exercicio.setMateria(materia);

        Object[] objetosBanca = row.getList("banca", String.class).toArray();
        for (Object objeto:objetosBanca
        ) {
            if (objeto instanceof String) {
                banca.add(objeto.toString());
            }
        }
        exercicio.setBanca(banca);

        Object[] objetosProva = row.getList("prova", String.class).toArray();
        for (Object objeto:objetosProva
        ) {
            if (objeto instanceof String) {
                prova.add(objeto.toString());
            }
        }
        exercicio.setProva(prova);

        Object[] objetosAno = row.getList("ano", Integer.class).toArray();
        for (Object objeto:objetosAno
        ) {
            if (objeto instanceof Integer) {
                ano.add((Integer)objeto);
            }
        }
        exercicio.setAno(ano);

        ComentarioDao comentarioDao = new ComentarioDao(sessao);
        exercicio.setComentarios(comentarioDao.busquePorExercicio(idExercicioTmp));

        return exercicio;
    }


}
