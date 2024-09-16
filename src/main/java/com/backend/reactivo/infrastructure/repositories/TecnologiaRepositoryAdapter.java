package com.backend.reactivo.infrastructure.repositories;

import org.springframework.data.domain.Pageable;

import com.backend.reactivo.domain.models.Tecnologia;
import com.backend.reactivo.domain.ports.out.TecnologiaRepositoryPort;
import com.backend.reactivo.infrastructure.entities.TecnologiaEntity;
import com.backend.reactivo.infrastructure.mappers.TecnologiaMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TecnologiaRepositoryAdapter implements TecnologiaRepositoryPort{
	
	private final TecnologiaRepository tecnologiaRepository;
	
	public TecnologiaRepositoryAdapter(TecnologiaRepository tecnologiaRepository) {
		this.tecnologiaRepository = tecnologiaRepository;
	}

	@Override
	public Mono<Tecnologia> save(Tecnologia tecnologia) {
		
		TecnologiaEntity tecnologiaEntity = TecnologiaMapper.toEntity(tecnologia);
		
		Mono<Tecnologia> monoTecnologia = TecnologiaMapper.toMonoDomain(tecnologiaRepository.save(tecnologiaEntity));
		
		return monoTecnologia;
	}

	@Override
	public Mono<Tecnologia> findById(Long id) {
		return TecnologiaMapper.toMonoDomain(tecnologiaRepository.findById(id));
	}

	@Override
	public Flux<Tecnologia> findAll() {
		return tecnologiaRepository.findAll().map(TecnologiaMapper::toDomain);
	}

	@Override
	public Mono<Tecnologia> findByNombre(String nombre) {
		return tecnologiaRepository.findByNombre(nombre).map(TecnologiaMapper::toDomain);
	}

	@Override
	public Flux<Tecnologia> findAllPag(Pageable pageable) {
		return tecnologiaRepository.findAllBy(pageable).map(TecnologiaMapper::toDomain);
	}

}
