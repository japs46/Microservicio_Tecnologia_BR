package com.backend.reactivo.domain.ports.in;

import com.backend.reactivo.application.respuesta.ResponseTecnologia;
import com.backend.reactivo.domain.models.Tecnologia;

import reactor.core.publisher.Mono;

public interface CreateTecnologiaUseCase {

	Mono<ResponseTecnologia> createTecnologia(Tecnologia tecnologia);
}
