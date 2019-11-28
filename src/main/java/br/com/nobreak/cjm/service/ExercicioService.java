package br.com.nobreak.cjm.service;

import br.com.nobreak.cjm.model.Exercicio;
import com.datastax.driver.core.ResultSet;

import java.util.List;
import java.util.UUID;

public interface ExercicioService {

    public Boolean registre(Exercicio exercicio);
    public List<Exercicio> listeCabecalhos();
    public Exercicio busquePorId(UUID id);
    public Exercicio busqueCabecalhoPorId(UUID id);
    public List<Exercicio> busqueCabecalhoPorMateriaEBancaEProvaEAno(String materia, String banca, String prova, Integer ano);
    public List<UUID> busqueCabecalhoPorMateriaEBancaEProvaEAnoParaAluno(String materia, String banca, String prova, Integer ano);
}
