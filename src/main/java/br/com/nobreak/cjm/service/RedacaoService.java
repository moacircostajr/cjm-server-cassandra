package br.com.nobreak.cjm.service;

import br.com.nobreak.cjm.model.Redacao;

import java.util.List;
import java.util.UUID;

public interface RedacaoService {
    public Boolean registreRedacao(Redacao redacao);
    public List<Redacao> busqueRedacoesPorIdAluno(UUID id);
    public Redacao busqueRedacaoPorId(UUID id);
    public List<Redacao> busqueRedacaoPorTema(String tema);
}
