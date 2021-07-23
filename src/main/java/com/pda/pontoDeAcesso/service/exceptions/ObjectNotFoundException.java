package com.pda.pontoDeAcesso.service.exceptions;

public class ObjectNotFoundException extends RuntimeException {
	
	/*Classe para tratar exce��es de objetos que n�o existem na persist�ncia.*/

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectNotFoundException(String message) {
		super(message);
	}
	
}
