package br.com.nobreak.cjm.service;

import br.com.nobreak.cjm.model.Colaborador;

import java.util.List;
import java.util.UUID;

public interface ColaboradorService {
    public Boolean registreOperador(Colaborador colaborador);
    public Colaborador busqueOperadorPorEmail(String email);
    public Colaborador busqueOperadorPorId(UUID id);
    public List<Colaborador> busqueOperadorPorNome(String nome);
    public List<Colaborador> listeTodosOperadores();
    public Colaborador verifiqueCredenciaisOperador(String email, String senha);
}
