package br.com.nobreak.cjm.controller;

import br.com.nobreak.cjm.model.Aluno;
import br.com.nobreak.cjm.model.Exercicio;
import br.com.nobreak.cjm.model.Redacao;
import br.com.nobreak.cjm.service.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/aluno")
public class AlunoController {

    private static final Logger log = LoggerFactory.getLogger(AlunoController.class);

    @Autowired
    AlunoServiceImpl alunoService;
    @Autowired
    RedacaoServiceImpl redacaoService;
    @Autowired
    ExercicioServiceImpl exercicioServiceImpl;
    @Autowired
    ClassificacaoPadraoServiceImpl classificacaoPadraoService;
    @Autowired
    ConcursosPadraoServiceImpl concursosPadraoService;
    @Autowired
    BancasPadraoServiceImpl bancasPadraoService;

    public AlunoController() {    }

    @PostMapping(value = "/inscricao")
    public @ResponseBody Boolean matricular(@RequestBody Aluno aluno) {
        Boolean resultado = alunoService.registreAluno(aluno);
        if (resultado == false) {
            log.info("Erro ao efetuar registro do aluno: " + aluno.getEmail());
        } else {
            log.info("Aluno registrado: " + aluno.getEmail());
        }
        return resultado;
    }

    @PostMapping(value = "{/id_aluno}/atualizar/")
    public @ResponseBody Boolean atualizarAluno(@PathVariable("id_aluno") UUID idAluno, @RequestBody Aluno aluno) {
        Boolean resultado = alunoService.atualizeAluno(aluno);
        if (resultado == false) {
            log.info("Erro ao atualizar o aluno: " + idAluno);
        } else {
            log.info("Aluno atualizado: " + idAluno);
        }
        return resultado;
    }


    @PostMapping(value = "{/id_aluno}/atualizarpontos/")
    public @ResponseBody Boolean atualizarPontosAluno(@PathVariable("id_aluno") UUID idAluno, @RequestBody Aluno aluno) {
        Boolean resultado = alunoService.atualizeEstatística(aluno);
        if (resultado == false) {
            log.info("Erro ao atualizar a pontuação do aluno: " + idAluno);
        } else {
            log.info("Atualizada a pontuação do aluno: " + idAluno);
        }
        return resultado;
    }

    @PostMapping(value = "/{id_aluno}/redacoes/enviar")
    public @ResponseBody Boolean receberRedacao(@PathVariable("id_aluno") UUID idAluno, @RequestBody Redacao redacao) {
        Boolean resultado = redacaoService.registreRedacao(redacao);
        if (resultado == false) {
            log.info("Erro ao efetuar registro da redação do aluno: " + idAluno);
        } else {
            log.info("Registrada a redação do aluno: " + idAluno);
        }
        return resultado;
    }

    @GetMapping(value = "/{id_aluno}/redacoes/buscar")
    public @ResponseBody List<Redacao> buscarRedacoesDoAluno(@PathVariable("id_aluno") UUID idAluno) {
        List<Redacao> redacoes = redacaoService.busqueRedacoesPorIdAluno(idAluno);
        if (redacoes.size() == 0) {
            log.info("Não foi encontrada nenhuma redação para o aluno: " + idAluno);
        } else {
            log.info("Redacões entregues ao aluno: " + idAluno);
        }
        return redacoes;
    }

    @GetMapping(value = "{/id_aluno}/redacao/buscar/{id_redacao}")
    public @ResponseBody Redacao buscarRedacao(@PathVariable("id_aluno") UUID idAluno, @PathVariable("id_redacao") UUID idRedacao) {
        Redacao redacao = redacaoService.busqueRedacaoPorId(idRedacao);
        if (redacao == null) {
            log.info("Erro ao entregar a redação solicitada pelo aluno: " + idAluno);
        } else {
            log.info("Redacão entregue ao aluno: " + idAluno);
        }
        return redacao;
    }

    /*TODO: corrigir essa merda*/
    @GetMapping(value = "/{id_aluno}/materia/{p_materia}/banca/{p_banca}/concurso/{p_concurso}/ano/{p_ano}")
    public @ResponseBody List<UUID> buscarExercicios(
            @PathVariable UUID id_aluno,
            @PathVariable String p_materia,
            @PathVariable String p_banca,
            @PathVariable String p_concurso,
            @PathVariable Integer p_ano
        ) {
        List<UUID> resultado = exercicioServiceImpl.busqueCabecalhoPorMateriaEBancaEProvaEAnoParaAluno(p_materia, p_banca, p_concurso, p_ano);
        if (resultado == null) {
            log.info("Erro ao efetuar a busca de exercicios. Aluno: " + id_aluno);
        } else {
            log.info("Lista de exercícios entregues ao aluno: " + id_aluno);
        }
        return resultado;
    }

    @GetMapping(value = "/{id_aluno}/exercicio/{id_exercicio}")
    public @ResponseBody Exercicio buscarExercicio(
            @PathVariable UUID id_aluno,
            @PathVariable UUID id_exercicio
    ) {
        Exercicio resultado = exercicioServiceImpl.busquePorId(id_exercicio);
        if (resultado == null) {
            log.info("Erro ao efetuar a busca de um exercício. Aluno: " + id_aluno);
        } else {
            log.info("Exercício entregue ao aluno: " + id_aluno);
        }
        return resultado;
    }

    @GetMapping(value="/{id_aluno}/classificacao")
    public @ResponseBody String acessarClassificacaoPadrao(@PathVariable("id_aluno") UUID idAluno) {
        String resultado = classificacaoPadraoService.buscaClassificacao();
        if (resultado == null) {
            log.info("Erro ao acessar classificação padrão. Aluno: " + idAluno);
        } else {
            log.info("Classificação padrão enviada. Aluno: " + idAluno);
        }
        return resultado;
    }

    @GetMapping(value="/{id_aluno}/concursos")
    public @ResponseBody List<String> buscarConcursosPadrao(@PathVariable("id_aluno") UUID idAluno) {
        List<String> resultado = concursosPadraoService.busque();
        if (resultado == null) {
            log.info("Erro ao acessar concursos padrão. Aluno: " + idAluno);
        } else {
            log.info("Concursos padrão enviados. Aluno: " + idAluno);
        }
        return resultado;
    }

    @GetMapping(value="/{id_aluno}/bancas")
    public @ResponseBody List<String> buscarBancasPadrao(@PathVariable("id_aluno") UUID idAluno) {
        List<String> resultado = bancasPadraoService.busque();
        if (resultado == null) {
            log.info("Erro ao acessar bancas padrão. Aluno: " + idAluno);
        } else {
            log.info("Bancas padrão enviadas. Aluno: " + idAluno);
        }
        return resultado;
    }

    @GetMapping(value="/{id_aluno}")
    public @ResponseBody Aluno buscarDadosAluno(@PathVariable("id_aluno") UUID idAluno) {
        Aluno resultado = alunoService.busqueAlunoPorId(idAluno);
        if (resultado == null) {
            log.info("Erro ao acessar os dados do aluno: " + idAluno);
        } else {
            log.info("Informações pessoais enviadas para o aluno: " + idAluno);
        }
        return resultado;
    }

    /*@GetMapping(value="/uuid")
    public void gerarUUID(){
        System.out.println(UUIDs.timeBased());
    }*/


    /*private Aluno converterAlunoDto(Aluno aluno) {
        Aluno alunoDto = new Aluno();
        alunoDto.setId(aluno.getId());
        alunoDto.setEmail(aluno.getEmail());
        alunoDto.setSenha(aluno.getSenha());
        alunoDto.setNome(aluno.getNome());
        alunoDto.setEndereco(aluno.getEndereco());
        alunoDto.setBairro(aluno.getBairro());
        alunoDto.setCidade(aluno.getCidade());
        alunoDto.setEstado(aluno.getEstado());
        alunoDto.setTelefone(aluno.getTelefone());
        alunoDto.setPontos(aluno.getPontos());
        return alunoDto;
    }*/

    /*TODO: comentar exercicio*/
    /*TODO: fazer exercicios FRONTEND*/

    /*TODO: na pagina inicial, todos os alunos poderão ver o ranking dos 10 mais pontuados*/
}
