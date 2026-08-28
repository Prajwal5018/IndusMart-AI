package com.indusmart.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String SECURITY_SCHEME_NAME = "bearerAuth";

    @Bean
    public OpenAPI indusMartOpenAPI() {

        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        return new OpenAPI()

                .info(new Info()
                        .title("IndusMart AI API")
                        .description("AI Powered Industrial Equipment Marketplace REST API")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Prajwal Shet")
                                .email("your-email@example.com"))
                        .license(new License()
                                .name("MIT License")))

                .externalDocs(new ExternalDocumentation()
                        .description("Project Documentation")
                        .url("https://github.com/"))

                .components(new Components()
                        .addSecuritySchemes(
                                SECURITY_SCHEME_NAME,
                                securityScheme
                        ))

                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(SECURITY_SCHEME_NAME)
                );
    }
}