package com.example.demo.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server()
                .url("http://localhost:8080")
                .description("Servidor de desenvolvimento");

        Contact contact = new Contact()
                .name("Equipe de Desenvolvimento")
                .email("dev@example.com");

        Info info = new Info()
                .title("API de Gerenciamento de Posto de Combustível")
                .version("1.0")
                .contact(contact)
                .description(
                        "API para gerenciamento de postos de combustível, incluindo controle de bombas, fornecedores, maquinários e reabastecimentos.")
                .license(new License().name("MIT License").url("https://opensource.org/licenses/MIT"));

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer));
    }
}