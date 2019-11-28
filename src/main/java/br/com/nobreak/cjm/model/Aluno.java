package br.com.nobreak.cjm.model;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter@Setter
public class Aluno {
    private UUID id;
    private String email;
    private String senha;
    private String nome;
    private String endereco;
    private String bairro;
    private String cidade;
    private String estado;
    private String telefone;
    private Integer pontos;
    private Integer questoes;
    private Integer acertos;
    private Integer erros;

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", telefone='" + telefone + '\'' +
                ", pontos=" + pontos +
                ", questoes=" + questoes +
                ", acertos=" + acertos +
                ", erros=" + erros +
                '}';
    }
}
