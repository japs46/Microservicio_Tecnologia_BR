package com.backend.reactivo.application.respuesta;

import com.backend.reactivo.domain.models.Tecnologia;

public class ResponseTecnologia {

	private String mensaje;
	
	private Tecnologia tecnologia;
	
	private boolean status;

	public ResponseTecnologia(String mensaje, Tecnologia tecnologia, boolean status) {
		this.mensaje = mensaje;
		this.tecnologia = tecnologia;
		this.status = status;
	}

	public String getMensaje() {
		return mensaje;
	}

	public Tecnologia getTecnologia() {
		return tecnologia;
	}

	public boolean isStatus() {
		return status;
	}
	
}
