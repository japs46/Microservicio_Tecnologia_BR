package com.backend.reactivo.infrastructure.mappers;

import com.backend.reactivo.domain.models.Tecnologia;
import com.backend.reactivo.infrastructure.entities.TecnologiaEntity;

import reactor.core.publisher.Mono;

public class TecnologiaMapper {

	public static Tecnologia toDomain(TecnologiaEntity entity) {
        return new Tecnologia(entity.getId(), entity.getNombre(), entity.getDescripcion());
    }

    public static TecnologiaEntity toEntity(Tecnologia tecnologia) {
        return new TecnologiaEntity(tecnologia.getId(), tecnologia.getNombre(), tecnologia.getDescripcion());
    }
    
    public static Mono<Tecnologia> toMonoDomain(Mono<TecnologiaEntity> monoTecnologiaEntity) {
		return monoTecnologiaEntity.map(TecnologiaMapper::toDomain);
	}
    
    public static Mono<TecnologiaEntity> toMonoEntity(Mono<Tecnologia> monoTecnologia) {
		return monoTecnologia.map(TecnologiaMapper::toEntity);
	}
	
}
