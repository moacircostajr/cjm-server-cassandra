package br.com.nobreak.cjm.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter@Setter
public class AlunoDto {
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
}
