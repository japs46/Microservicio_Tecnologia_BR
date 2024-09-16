package com.backend.reactivo;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.backend.reactivo.application.respuesta.ResponseTecnologia;
import com.backend.reactivo.application.services.TecnologiaService;
import com.backend.reactivo.domain.models.Tecnologia;
import com.backend.reactivo.infrastructure.controllers.TecnologiaController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@WebFluxTest(TecnologiaController.class)
public class TecnologiaControllerTest {

	@Autowired 
	private WebTestClient webTestClient;
	
	@MockBean
	private TecnologiaService tecnologiaService;
	
	@Test
	public void crearTecnologia() {
		Tecnologia tecnologia = new Tecnologia(90L, "Java", "descripcion");
		Mono<ResponseTecnologia> monoTecnologia= Mono.just(new ResponseTecnologia("Ok", tecnologia, true));
		when(tecnologiaService.createTecnologia(any())).thenReturn(monoTecnologia);
		
		webTestClient.post().uri("/api/tecnologias/guardar").bodyValue(tecnologia)
		.exchange()
		.expectStatus().isCreated();
		 verify(tecnologiaService,times(1)).createTecnologia(any());
	}
	
	@Test
	public void obtenerListadoTecnologiasPaginadas() {
		Flux<Tecnologia> fluxTecnologia= Flux.just(new Tecnologia(1L, "Java", "descripcion java"),
				new Tecnologia(2L,"Python","descripcion python"),
				new Tecnologia(3L,"JavaScript","descripcion JavaScript"),
				new Tecnologia(4L,"Postman","desxripcion postman"),
				new Tecnologia(5L,"SQL","descripcion sql"),
				new Tecnologia(6L,"NoSQL","descripcion nosql"));
		
		Flux<Tecnologia> test1 = fluxTecnologia.filter(tecnologia -> tecnologia.getId() == 1 || tecnologia.getId() == 2);
		Flux<Tecnologia> test2 = fluxTecnologia.filter(tecnologia -> tecnologia.getId() == 5 || tecnologia.getId() == 6);
		Flux<Tecnologia> test3 = fluxTecnologia.filter(tecnologia -> tecnologia.getId() == 3 || tecnologia.getId() == 4);
		
		when(tecnologiaService.getAllTecnologiasPag(0, 2, "asc")).thenReturn(test1);
		when(tecnologiaService.getAllTecnologiasPag(0, 2, "desc")).thenReturn(test2);
		when(tecnologiaService.getAllTecnologiasPag(1, 2, "asc")).thenReturn(test3);
		when(tecnologiaService.getAllTecnologiasPag(0, 6, "asc")).thenReturn(fluxTecnologia);
		
//		webTestClient.get().uri("/api/tecnologias/obtenerPag?page=0&size=2&direction=asc")
//		.exchange()
//		.expectStatus().isOk()
//		.expectBody()
//		.jsonPath("$[0].id").isEqualTo(1L)
//		.jsonPath("$[0].nombre").isEqualTo("Java");
		
		webTestClient.get().uri("/api/tecnologias/obtenerPag?page=0&size=2&direction=asc")
	    .exchange()
	    .expectStatus().isOk()
	    .expectBodyList(Tecnologia.class)
	    .consumeWith(response -> {
	        List<Tecnologia> tecnologias = response.getResponseBody();
	        assertNotNull(tecnologias);
	        assertFalse(tecnologias.isEmpty());
	        assertEquals(2,tecnologias.size());
	        assertEquals("Java", tecnologias.get(0).getNombre());
	        assertEquals(1L, tecnologias.get(0).getId());
	    });
		
		webTestClient.get().uri("/api/tecnologias/obtenerPag?page=0&size=2&direction=desc")
	    .exchange()
	    .expectStatus().isOk()
	    .expectBodyList(Tecnologia.class)
	    .consumeWith(response -> {
	        List<Tecnologia> tecnologias = response.getResponseBody();
	        assertNotNull(tecnologias);
	        assertFalse(tecnologias.isEmpty());
	        assertEquals(2,tecnologias.size());
	        assertEquals("SQL", tecnologias.get(0).getNombre());
	        assertEquals(5L, tecnologias.get(0).getId());
	    });
		
		verify(tecnologiaService,times(1)).getAllTecnologiasPag(0, 2, "asc");
		verify(tecnologiaService,times(1)).getAllTecnologiasPag(0, 2, "desc");
	}

}
