package br.com.nobreak.cjm.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter@Setter
public class Redacao {
    private UUID id;
    private UUID idAluno;
    private String tema;
    private Integer pontuacao;
    /*deve ser aceito somente o formato jpg*/
    private String redacaoOriginal;
    private String redacaoCorrigida;

    @Override
    public String toString() {
        return "Redacao{" +
                "id=" + id +
                ", idAluno=" + idAluno +
                ", tema='" + tema + '\'' +
                ", pontuacao=" + pontuacao +
                ", redacaoOriginal='" + redacaoOriginal + '\'' +
                ", redacaoCorrigida='" + redacaoCorrigida + '\'' +
                '}';
    }
}
