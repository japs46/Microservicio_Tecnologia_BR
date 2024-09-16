package com.backend.reactivo.infrastructure.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.reactivo.infrastructure.entities.TecnologiaEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface TecnologiaRepository extends ReactiveCrudRepository<TecnologiaEntity, Long>{

	Mono<TecnologiaEntity> findByNombre(String nombre);
	
	Flux<TecnologiaEntity> findAllBy(Pageable pageable);
}
