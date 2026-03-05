package br.com.ehc.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title("REST API´s RESTFull from 0 with Java, Spring Boot, Kubernates and Docker")
                    .version("v1")
                    .description("Curso completo de Java do 0 ao avançado")
                    .termsOfService("https://ehc.com.br/meus-cursos")
                    .license(new License()
                        .name("Apache 2.0")
                        .url("https://ehc.com.br/blog")
                    )
                );
    }
}
