package com.backend.reactivo.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().
				info(new Info()
						.title("Api Tecnologias")
						.version("1.0")
						.description("Documentación de la API para gestionar tecnologías"));
	}
}