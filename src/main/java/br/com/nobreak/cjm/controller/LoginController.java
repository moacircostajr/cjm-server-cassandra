package br.com.nobreak.cjm.controller;

import br.com.nobreak.cjm.controller.dto.AlunoDto;
import br.com.nobreak.cjm.controller.dto.LoginDto;
import br.com.nobreak.cjm.model.Aluno;
import br.com.nobreak.cjm.service.impl.AlunoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    AlunoServiceImpl alunoService;

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @PostMapping(value = "/aluno/login")
    public @ResponseBody UUID logarAluno(@RequestBody LoginDto login) {
        UUID resultado = null;
        Aluno aluno = alunoService.verifiqueCredenciaisAluno(login.getEmail(), login.getSenha());
        if (aluno == null) {
            log.info("Tentativa de acesso frustrada. Aluno: " + login.getEmail());
        } else {
            resultado = aluno.getId();
            log.info("Acesso liberado para o aluno: " + login.getEmail());
        }
        return resultado;
    }

    AlunoDto prepararAlunoDto(Aluno aluno) {
        AlunoDto alunoDto = new AlunoDto();
        alunoDto.setId(aluno.getId());
        alunoDto.setEmail(aluno.getEmail());
        alunoDto.setNome(aluno.getNome());
        alunoDto.setEndereco(aluno.getEndereco());
        alunoDto.setBairro(aluno.getBairro());
        alunoDto.setCidade(aluno.getCidade());
        alunoDto.setEstado(aluno.getEstado());
        alunoDto.setTelefone(aluno.getTelefone());
        alunoDto.setPontos(aluno.getPontos());
        alunoDto.setQuestoes(aluno.getQuestoes());
        alunoDto.setAcertos(aluno.getAcertos());
        alunoDto.setErros(aluno.getErros());
        return alunoDto;
    }
}
