package br.com.nobreak.cjm.service;

import br.com.nobreak.cjm.model.Aluno;

import java.util.List;
import java.util.UUID;

public interface AlunoService {
    public Boolean registreAluno (Aluno aluno);
    public Boolean atualizeAluno (Aluno aluno);
    public Boolean atualizeEstatística(Aluno aluno);
    public Aluno busqueAlunoPorId (UUID id);
    public Aluno busqueAlunoPorEmail (String email);
    public List<Aluno> busqueAlunoPorNome (String nome);
    public List<Aluno> listeTodosAlunos();
    public Aluno verifiqueCredenciaisAluno(String email, String senha);
}
