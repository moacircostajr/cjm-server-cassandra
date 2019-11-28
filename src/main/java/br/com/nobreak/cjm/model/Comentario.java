package br.com.nobreak.cjm.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter@Setter
public class Comentario {
    private Long id;
    private UUID idExercicio;
    private String email;
    private String comentario;

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", idExercicio=" + idExercicio +
                ", email='" + email + '\'' +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
