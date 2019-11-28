package br.com.nobreak.cjm.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter@Setter
public class Colaborador {
    private UUID id;
    private String email;
    private String senha;
    private String nome;
    private String telefone;
    private Perfil perfil;

    @Override
    public String toString() {
        return "Colaborador{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", perfil=" + perfil +
                '}';
    }
}
