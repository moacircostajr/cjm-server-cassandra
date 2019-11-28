package br.com.nobreak.cjm.controller;

import br.com.nobreak.cjm.model.Exercicio;
import br.com.nobreak.cjm.model.Colaborador;
import br.com.nobreak.cjm.service.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/A0J06Yp7")
public class OperadorController {

    private static final Logger log = LoggerFactory.getLogger(OperadorController.class);

    @Autowired
    ColaboradorServiceImpl operadorService;
    @Autowired
    ExercicioServiceImpl exercicioService;
    @Autowired
    ClassificacaoPadraoServiceImpl classificacaoPadraoService;
    @Autowired
    BancasPadraoServiceImpl bancasPadraoService;
    @Autowired
    ConcursosPadraoServiceImpl concursosPadraoService;

    public OperadorController() {    }

    @PostMapping(value = "/logar")
    public Colaborador logar(@RequestBody String email, @RequestBody String senha) {
        return operadorService.verifiqueCredenciaisOperador(email, senha);
    }

    @PostMapping(value = "/registrar/operador")
    public @ResponseBody Boolean registrarOperador(@RequestBody Colaborador colaborador) {
        Boolean resultado = operadorService.registreOperador(colaborador);
        if (resultado == false) {
            log.info("Erro ao efetuar registro do colaborador: " + colaborador.getEmail());
        } else {
            log.info("Colaborador registrado: " + colaborador.getEmail());
        }
        return resultado;
    }

    @PostMapping(value = "/registrar/exercicio")
    public @ResponseBody Boolean registrarExercicio(@RequestBody Exercicio exercicio) {
        Boolean resultado = exercicioService.registre(exercicio);
        if (resultado == false) {
            log.info("Erro ao efetuar registro do exercicio: " + exercicio.getId());
        } else {
            log.info("Exercicio registrado: " + exercicio.getId());
        }
        return resultado;
    }

    @GetMapping(value="/exercicios")
    public @ResponseBody List<Exercicio> listarExercicios() {
        List<Exercicio> resultado = exercicioService.listeCabecalhos();
        if (resultado == null) {
            log.info("Erro ao efetuar listagem de exercicios.");
        } else {
            log.info("Exercicios listados.");
        }
        return resultado;
    }

    @GetMapping(value="/exercicio/{id}")
    public @ResponseBody Exercicio acessarExercicio(@PathVariable("id") UUID idExercicio) {
        Exercicio resultado = exercicioService.busquePorId(idExercicio);
        if (resultado == null) {
            log.info("Erro ao acessar exercicio.");
        } else {
            log.info("Exercicio entregue.");
        }
        return resultado;
    }

    @PostMapping(value="/registrar/classificacao")
    public @ResponseBody Boolean registrarClassificacao(@RequestBody String classificacao) {
        Boolean resultado = classificacaoPadraoService.registraClassificacao(classificacao);
        if (resultado == false) {
            log.info("Erro ao efetuar registro da classificação.");
        } else {
            log.info("Classificação registrada.");
        }
        return resultado;
    }

    @GetMapping(value="/classificacao")
    public @ResponseBody String acessarClassificacao() {
        String resultado = classificacaoPadraoService.buscaClassificacao();
        if (resultado == null) {
            log.info("Erro ao acessar classificação padrão.");
        } else {
            log.info("Classificação padrão enviada.");
        }
        return resultado;
    }

    @PostMapping(value="/registrar/bancas")
    public @ResponseBody Boolean registrarBancas(@RequestBody List<String> bancas) {
        Boolean resultado = bancasPadraoService.registre(bancas);
        if (resultado == false) {
            log.info("Erro ao efetuar registro das bancas padrão.");
        } else {
            log.info("Bancas padrão registradas.");
        }
        return resultado;
    }

    @GetMapping(value="/bancas")
    public @ResponseBody List<String> buscarBancas() {
        List<String> resultado = bancasPadraoService.busque();
        if (resultado == null) {
            log.info("Erro ao acessar bancas padrão.");
        } else {
            log.info("Bancas padrão enviadas.");
        }
        return resultado;
    }

    @PostMapping(value="/registrar/concursos")
    public @ResponseBody Boolean registrarConcursos(@RequestBody List<String> concursos) {
        Boolean resultado = concursosPadraoService.registre(concursos);
        if (resultado == false) {
            log.info("Erro ao efetuar registro dos concursos padrão.");
        } else {
            log.info("Concursos padrão registrados.");
        }
        return resultado;
    }

    @GetMapping(value="/concursos")
    public @ResponseBody List<String> buscarConcursos() {
        List<String> resultado = concursosPadraoService.busque();
        if (resultado == null) {
            log.info("Erro ao acessar concursos padrão.");
        } else {
            log.info("Concursos padrão enviados.");
        }
        return resultado;
    }
    /*TODO: acessar redacoes para correção*/

    /*TODO: acessar progresso do aluno*/

}
