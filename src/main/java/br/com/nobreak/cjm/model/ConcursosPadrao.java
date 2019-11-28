package br.com.nobreak.cjm.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class ConcursosPadrao {

    private String id;
    private List<String> concursos;
}
