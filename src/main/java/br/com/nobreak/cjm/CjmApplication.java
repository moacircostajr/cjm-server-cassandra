package br.com.nobreak.cjm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* PASSO 2:
*    EXTENDS AbstractSecurityWebApplicationInitializer
*    Register the springSecurityFilterChain Filter for every URL in your application
 * */

@SpringBootApplication
public class CjmApplication {

    public static void main(String[] args) {
        SpringApplication.run(CjmApplication.class, args);
    }

}

