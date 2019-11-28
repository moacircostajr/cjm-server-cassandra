package br.com.nobreak.cjm.service;

import java.util.List;

public interface BancasPadraoService {
    public boolean registre(List<String> bancas);
    public List<String> busque();
}
