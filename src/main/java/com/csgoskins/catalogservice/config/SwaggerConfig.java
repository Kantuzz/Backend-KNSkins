package com.csgoskins.catalogservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI swaggerApi() {

        // Esquema de seguridad tipo Bearer para JWT
        SecurityScheme bearerAuth = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");

        return new OpenAPI()
                .info(new Info()
                        .title("CSGO Skins API")
                        .version("1.0.0")
                        .description("Documentación oficial de la API del proyecto CSGO Skins.\nIncluye JWT, roles y endpoints protegidos.")
                        .contact(new Contact()
                                .name("Noah — Proyecto CSGO Skins")
                                .email("no-reply@csgo-skins.cl"))
                        .license(new License().name("MIT License")))
                // Registramos BearerAuth en Swagger
                .components(new Components().addSecuritySchemes("bearerAuth", bearerAuth))
                // Aplicamos Auth globalmente
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }
}
