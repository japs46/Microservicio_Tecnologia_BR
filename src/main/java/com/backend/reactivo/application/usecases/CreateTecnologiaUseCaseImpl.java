package com.backend.reactivo.application.usecases;

import org.springframework.stereotype.Component;

import com.backend.reactivo.application.respuesta.ResponseTecnologia;
import com.backend.reactivo.domain.models.Tecnologia;
import com.backend.reactivo.domain.ports.in.CreateTecnologiaUseCase;
import com.backend.reactivo.domain.ports.out.TecnologiaRepositoryPort;

import reactor.core.publisher.Mono;

@Component
public class CreateTecnologiaUseCaseImpl implements CreateTecnologiaUseCase{

	private final TecnologiaRepositoryPort tecnologiaRepositoryPort;
	
	public CreateTecnologiaUseCaseImpl(TecnologiaRepositoryPort tecnologiaRepositoryPort) {
		this.tecnologiaRepositoryPort = tecnologiaRepositoryPort;
	}

	@Override
	public Mono<ResponseTecnologia> createTecnologia(Tecnologia tecnologia) {
		return tecnologiaRepositoryPort.findByNombre(tecnologia.getNombre())
		        .flatMap(existingTecnologia -> {
		        	return Mono.just(new ResponseTecnologia("Ya existe una tecnologia con ese nombre", existingTecnologia,false));
		            
		        })
		        .switchIfEmpty(
		            tecnologiaRepositoryPort.save(tecnologia).flatMap(tecnologi->{
		            	return Mono.just(new ResponseTecnologia("Tecnologia Creada Correctamente.", tecnologi,true));
		            })
		        );
	}

}
