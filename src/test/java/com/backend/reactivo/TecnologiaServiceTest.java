package com.backend.reactivo;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.reactivo.application.respuesta.ResponseTecnologia;
import com.backend.reactivo.application.services.TecnologiaService;
import com.backend.reactivo.domain.models.Tecnologia;
import com.backend.reactivo.domain.ports.in.CreateTecnologiaUseCase;
import com.backend.reactivo.domain.ports.in.RetrieveTecnologiaUseCase;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
public class TecnologiaServiceTest {

	@Mock
	private CreateTecnologiaUseCase createTecnologiaUseCase;

	@Mock
	private RetrieveTecnologiaUseCase retrieveTecnologiaUseCase;

	@InjectMocks
	private TecnologiaService tecnologiaService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); // Inicializa los mocks
	}

	@Test
	public void createTecnologiaTest() {
		Tecnologia tecnologia = new Tecnologia(90L, "Java", "descripcion");
		Mono<ResponseTecnologia> monoResponse= Mono.just(new ResponseTecnologia("Se registro correctamente", tecnologia, false));
		when(createTecnologiaUseCase.createTecnologia(tecnologia)).thenReturn(monoResponse);

		StepVerifier.create(tecnologiaService.createTecnologia(tecnologia)).expectSubscription()
				.expectNextCount(1).verifyComplete();
		
		verify(createTecnologiaUseCase).createTecnologia(tecnologia);

	}
	
	@Test
	public void obtenerTecnologiasPaginadasTest() {
		Flux<Tecnologia> fluxTecnologia= Flux.just(new Tecnologia(1L, "Java", "descripcion java"),
				new Tecnologia(2L,"Python","descripcion python"),
				new Tecnologia(3L,"JavaScript","descripcion JavaScript"),
				new Tecnologia(4L,"Postman","desxripcion postman"),
				new Tecnologia(5L,"SQL","descripcion sql"),
				new Tecnologia(6L,"NoSQL","descripcion nosql"));
		
		Flux<Tecnologia> test1 = fluxTecnologia.filter(tecnologia -> tecnologia.getId() == 1 || tecnologia.getId() == 2);
		Flux<Tecnologia> test2 = fluxTecnologia.filter(tecnologia -> tecnologia.getId() == 5 || tecnologia.getId() == 6);
		Flux<Tecnologia> test3 = fluxTecnologia.filter(tecnologia -> tecnologia.getId() == 3 || tecnologia.getId() == 4);
		
		when(retrieveTecnologiaUseCase.getAllTecnologiasPag(0, 2, "asc")).thenReturn(test1);
		when(retrieveTecnologiaUseCase.getAllTecnologiasPag(0, 2, "desc")).thenReturn(test2);
		when(retrieveTecnologiaUseCase.getAllTecnologiasPag(1, 2, "asc")).thenReturn(test3);
		when(retrieveTecnologiaUseCase.getAllTecnologiasPag(0, 6, "asc")).thenReturn(fluxTecnologia);
		
		StepVerifier.create(tecnologiaService.getAllTecnologiasPag(0, 2, "asc").log())
		.expectSubscription()
		.expectNextMatches(tecnology -> tecnology.getNombre().equals("Java"))
		.expectNextMatches(tecnology -> tecnology.getNombre().equals("Python"))
		.expectComplete();
		
		StepVerifier.create(tecnologiaService.getAllTecnologiasPag(0, 6, "asc").log())
		.expectSubscription()
		.expectNextCount(6)
		.expectComplete();
		
		verify(retrieveTecnologiaUseCase,times(1)).getAllTecnologiasPag(0, 2, "asc");
		verify(retrieveTecnologiaUseCase,times(1)).getAllTecnologiasPag(0, 6, "asc");

	}
}
