package br.com.nobreak.cjm.controller.dto;

import br.com.nobreak.cjm.model.Comentario;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter@Setter
public class ExercicioDto {

    private UUID id;
    private String enunciado1;
    private String imagemEnunciado;
    private String enunciado2;
    private List<String> opcoes;
    private Integer gabaritoObjetivo;
    private String gabaritoSubjetivo;
    private Integer pontuacao;
    private List<String> materia;
    private List<String> banca;
    private List<String> prova;
    private List<Integer> ano;
    private List<Comentario> comentarios;
}
