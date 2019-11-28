package br.com.nobreak.cjm.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class LoginDto {
    private String email;
    private String senha;
}
