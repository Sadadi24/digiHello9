package com.digi.digiHello9.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("aPI DE gESTION DES VILLES ET DÉPARTEMENTS")
                        .version("1.0")
                        .description("Cette API fournit des services de gestion et de consultation des données rélatives aux villes et départements francais.")
                        .contact(new Contact().name("Sadadi Assani").email("sassani@diginamic-formation.fr")));
    }
}
