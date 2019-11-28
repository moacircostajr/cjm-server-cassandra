package br.com.nobreak.cjm.config;


import br.com.nobreak.cjm.database_cassandra.Conexao;
import br.com.nobreak.cjm.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ConfigBeans {

    /*@Bean
    Conexao criaConexaoCassandra() {
        final Conexao conexaoCassandra = new Conexao();
        return conexaoCassandra;
    }*/

    @Bean
    public AlunoServiceImpl criarAlunoService() {
        final AlunoServiceImpl alunoService = new AlunoServiceImpl();
        return alunoService;
    }

    @Bean
    public ColaboradorServiceImpl criarOperadorService() {
        final ColaboradorServiceImpl operadorService = new ColaboradorServiceImpl();
        return operadorService;
    }

    @Bean
    public ExercicioServiceImpl criaExercicioService() {
        final ExercicioServiceImpl exercicioService = new ExercicioServiceImpl();
        return exercicioService;
    }

    @Bean
    public RedacaoServiceImpl criaRedacaoService() {
        final RedacaoServiceImpl redacaoService = new RedacaoServiceImpl();
        return redacaoService;
    }

    @Bean
    ClassificacaoPadraoServiceImpl criaClassificacaoPadraoService() {
        final ClassificacaoPadraoServiceImpl classificacaoPadraoService = new ClassificacaoPadraoServiceImpl();
        return classificacaoPadraoService;
    }

    @Bean
    BancasPadraoServiceImpl criaBancasPadraoService() {
        final BancasPadraoServiceImpl bancasPadraoService = new BancasPadraoServiceImpl();
        return bancasPadraoService;
    }

    @Bean
    ConcursosPadraoServiceImpl criaConcursosPadraoService() {
        final ConcursosPadraoServiceImpl concursosPadraoService = new ConcursosPadraoServiceImpl();
        return concursosPadraoService;
    }

}
