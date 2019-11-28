package br.com.nobreak.cjm.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter@Setter
public class Exercicio {
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

    @Override
    public String toString() {
        return "Exercicio{" +
                "id=" + id +
                ", enunciado1='" + enunciado1 + '\'' +
                ", imagemEnunciado='" + imagemEnunciado + '\'' +
                ", enunciado2='" + enunciado2 + '\'' +
                ", opcoes=" + opcoes +
                ", gabaritoObjetivo=" + gabaritoObjetivo +
                ", gabaritoSubjetivo='" + gabaritoSubjetivo + '\'' +
                ", pontuacao=" + pontuacao +
                ", materia=" + materia +
                ", banca=" + banca +
                ", prova=" + prova +
                ", ano=" + ano +
                ", comentarios=" + comentarios +
                '}';
    }
}
