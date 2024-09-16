package com.backend.reactivo.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backend.reactivo.application.services.TecnologiaService;
import com.backend.reactivo.application.usecases.CreateTecnologiaUseCaseImpl;
import com.backend.reactivo.application.usecases.RetrieveTecnologiaUseCaseImpl;
import com.backend.reactivo.domain.ports.out.TecnologiaRepositoryPort;
import com.backend.reactivo.infrastructure.repositories.TecnologiaRepository;
import com.backend.reactivo.infrastructure.repositories.TecnologiaRepositoryAdapter;

@Configuration
public class ApplicationConfig {

	@Bean
	public TecnologiaService tecnologiaService(TecnologiaRepositoryPort tecnologiaRepositoryPort) {
		return new TecnologiaService(new CreateTecnologiaUseCaseImpl(tecnologiaRepositoryPort), 
				new RetrieveTecnologiaUseCaseImpl(tecnologiaRepositoryPort));
	}
	
	@Bean
	public TecnologiaRepositoryPort tecnologiaRepositoryPort(TecnologiaRepositoryAdapter tecnologiaRepositoryAdapter) {
		return tecnologiaRepositoryAdapter;
	}
	
	@Bean
	public TecnologiaRepositoryAdapter tecnologiaRepositoryAdapter(TecnologiaRepository tecnologiaRepository) {
		return new TecnologiaRepositoryAdapter(tecnologiaRepository);
	}
	
}
