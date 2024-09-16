package com.backend.reactivo.infrastructure.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.reactivo.application.respuesta.ResponseTecnologia;
import com.backend.reactivo.application.services.TecnologiaService;
import com.backend.reactivo.domain.models.Tecnologia;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/tecnologias")
public class TecnologiaController {
	
	private final TecnologiaService tecnologiaService;

	public TecnologiaController(TecnologiaService tecnologiaService) {
		this.tecnologiaService = tecnologiaService;
	}
	
    @Operation(summary = "Crear una nueva tecnología", description = "Guarda una nueva tecnología en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Tecnología guardada exitosamente")
    @ApiResponse(responseCode = "406", description = "No se aceptó la solicitud")
	@PostMapping("/guardar")
	public Mono<ResponseEntity<ResponseTecnologia>> crearTecnologia(@RequestBody Tecnologia tecnologia){
    	return tecnologiaService.createTecnologia(tecnologia)
    			.map(responsePrueba-> {
    				if (responsePrueba.isStatus()) {
    					return ResponseEntity.status(HttpStatus.CREATED).body(responsePrueba);
					}
    				 return ResponseEntity.status(HttpStatus.ACCEPTED).body(responsePrueba);
    			})
    			.defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
	}
	

    @Operation(summary = "Obtener tecnología por ID", description = "Recupera una tecnología por su ID.")
    @ApiResponse(responseCode = "200", description = "Tecnología encontrada")
    @ApiResponse(responseCode = "404", description = "Tecnología no encontrada")
	@GetMapping("/obtener/{id}")
	public Mono<ResponseEntity<Tecnologia>> obtenerTecnologia(@PathVariable Long id){
		return tecnologiaService.getTecnologia(id)
				.map(tecnologia -> ResponseEntity.ok(tecnologia))
				.defaultIfEmpty(new ResponseEntity<Tecnologia>(HttpStatus.NOT_FOUND));
	}
	
    @Operation(summary = "Obtener todas las tecnologías", description = "Recupera todas las tecnologías disponibles.")
    @ApiResponse(responseCode = "200", description = "Lista de tecnologías")
	@GetMapping("/obtener")
	public Flux<Tecnologia> obtenerTecnologias(){
		return tecnologiaService.getAllTecnologias();
	}
    
    @GetMapping("/obtenerPag")
    public Flux<Tecnologia> obtenerTecnologiasPaginadas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "asc") String direction) {

        return tecnologiaService.getAllTecnologiasPag(page, size, direction);
    }
	
}
