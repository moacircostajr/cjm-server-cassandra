package br.com.nobreak.cjm.service;

import java.util.List;

public interface ConcursosPadraoService {
    public boolean registre(List<String> concursos);
    public List<String> busque();
}
