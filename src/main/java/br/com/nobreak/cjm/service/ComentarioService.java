package br.com.nobreak.cjm.service;

import br.com.nobreak.cjm.model.Comentario;

import java.util.List;
import java.util.UUID;

public interface ComentarioService {
    public Comentario registre(Comentario comentario);
    public Comentario busquePorId(Long id);
    public List<Comentario> busquePorEmail(String email);
}
