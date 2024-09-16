package com.backend.reactivo.domain.ports.in;

import com.backend.reactivo.domain.models.Tecnologia;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RetrieveTecnologiaUseCase {

	Mono<Tecnologia> getTecnologia(Long id);
	
	Mono<Tecnologia> getTecnologiaByNombre(String nombre);
	
	Flux<Tecnologia> getAllTecnologias();
	
	Flux<Tecnologia> getAllTecnologiasPag(int page, int size, String direction);
}
