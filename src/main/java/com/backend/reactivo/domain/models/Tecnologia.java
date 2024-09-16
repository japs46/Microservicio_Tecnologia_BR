package com.backend.reactivo.domain.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Tecnologia {

	private final Long id;
	
	@NotNull(message = "El nombre de la tecnologia es requerido")
	@NotEmpty(message = "El nombre de la tecnologia es requerido")
	@Size(max = 50, message= "El nombre debe tener 50 caracteres como maximo")
	private final String nombre;
	
	@NotNull(message = "La descripcion de la tecnologia es requerido")
	@NotEmpty(message = "La descripcion de la tecnologia es requerido")
	@Size(max = 90, message= "El nombre debe tener 50 caracteres como maximo")
	private final String descripcion;
	
	public Tecnologia(Long id, String nombre, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
//	@Override
//	public String toString() {
//		return "Tecnologia{" +
//	               "id=" + id +
//	               ", nombre='" + nombre + '\'' +
//	               ", descripcion='" + descripcion + '\'' +
//	               '}';
//	}
//	
}
