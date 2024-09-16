package com.backend.reactivo.application.services;

import org.springframework.stereotype.Service;

import com.backend.reactivo.application.respuesta.ResponseTecnologia;
import com.backend.reactivo.domain.models.Tecnologia;
import com.backend.reactivo.domain.ports.in.CreateTecnologiaUseCase;
import com.backend.reactivo.domain.ports.in.RetrieveTecnologiaUseCase;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TecnologiaService implements CreateTecnologiaUseCase,RetrieveTecnologiaUseCase{

	private final CreateTecnologiaUseCase createTecnologiaUseCase;
	private final RetrieveTecnologiaUseCase retrieveTecnologiaUseCase;

	public TecnologiaService(CreateTecnologiaUseCase createTecnologiaUseCase,
			RetrieveTecnologiaUseCase retrieveTecnologiaUseCase) {
		this.createTecnologiaUseCase = createTecnologiaUseCase;
		this.retrieveTecnologiaUseCase = retrieveTecnologiaUseCase;
	}

	@Override
	public Mono<Tecnologia> getTecnologia(Long id) {
		return retrieveTecnologiaUseCase.getTecnologia(id);
	}

	@Override
	public Flux<Tecnologia> getAllTecnologias() {
		return retrieveTecnologiaUseCase.getAllTecnologias();
	}

	@Override
	public Mono<ResponseTecnologia> createTecnologia(Tecnologia tecnologia) {
		return createTecnologiaUseCase.createTecnologia(tecnologia);
	}

	@Override
	public Mono<Tecnologia> getTecnologiaByNombre(String nombre) {
		return retrieveTecnologiaUseCase.getTecnologiaByNombre(nombre);
	}

	@Override
	public Flux<Tecnologia> getAllTecnologiasPag(int page, int size, String direction) {
		return retrieveTecnologiaUseCase.getAllTecnologiasPag(page,size,direction);
	}

}
