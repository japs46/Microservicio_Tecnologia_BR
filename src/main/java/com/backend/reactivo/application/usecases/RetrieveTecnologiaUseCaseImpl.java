package com.backend.reactivo.application.usecases;

import org.springframework.stereotype.Component;

import com.backend.reactivo.domain.models.Tecnologia;
import com.backend.reactivo.domain.ports.in.RetrieveTecnologiaUseCase;
import com.backend.reactivo.domain.ports.out.TecnologiaRepositoryPort;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RetrieveTecnologiaUseCaseImpl implements RetrieveTecnologiaUseCase{

	private final TecnologiaRepositoryPort tecnologiaRepositoryPort;
	
	public RetrieveTecnologiaUseCaseImpl(TecnologiaRepositoryPort tecnologiaRepositoryPort) {
		this.tecnologiaRepositoryPort = tecnologiaRepositoryPort;
	}

	@Override
	public Mono<Tecnologia> getTecnologia(Long id) {
		return tecnologiaRepositoryPort.findById(id);
	}

	@Override
	public Flux<Tecnologia> getAllTecnologias() {
		return tecnologiaRepositoryPort.findAll();
	}

	@Override
	public Mono<Tecnologia> getTecnologiaByNombre(String nombre) {
		return tecnologiaRepositoryPort.findByNombre(nombre);
	}

	@Override
	public Flux<Tecnologia> getAllTecnologiasPag(int page, int size, String direction) {
		return tecnologiaRepositoryPort.findAll()
                .sort((tec1, tec2) -> {
                    if ("desc".equalsIgnoreCase(direction)) {
                        return tec2.getNombre().compareTo(tec1.getNombre());
                    } else {
                        return tec1.getNombre().compareTo(tec2.getNombre());
                    }
                })
                .skip((long) page * size)
                .take(size); 
	}
	
	
}
