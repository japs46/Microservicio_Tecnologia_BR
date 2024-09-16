package com.backend.reactivo.domain.ports.out;

import org.springframework.data.domain.Pageable;

import com.backend.reactivo.domain.models.Tecnologia;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TecnologiaRepositoryPort {

	Mono<Tecnologia> save(Tecnologia tecnologia);
	
	Mono<Tecnologia> findById(Long id);
	
	Flux<Tecnologia> findAll();
	
	Mono<Tecnologia> findByNombre(String nombre);
	
	Flux<Tecnologia> findAllPag(Pageable pageable);
}
