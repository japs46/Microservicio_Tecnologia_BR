package com.backend.reactivo.infrastructure.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolationException;
import reactor.core.publisher.Mono;

@Component
public class GlobalExceptionHandler implements WebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        if (ex instanceof ConstraintViolationException) {
            return handleConstraintViolationException(exchange, (ConstraintViolationException) ex);
        }

        // Manejo de otras excepciones
        return Mono.error(ex);
    }

    private Mono<Void> handleConstraintViolationException(ServerWebExchange exchange, ConstraintViolationException ex) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        byte[] bytes=null;
		try {
			bytes = new ObjectMapper().writeValueAsBytes(errorResponse);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return response.writeWith(Mono.just(response.bufferFactory().wrap(bytes)));
    }
}