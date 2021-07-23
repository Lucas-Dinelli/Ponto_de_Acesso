package com.pda.pontoDeAcesso.service.exceptions;

public class DataIntegrityViolationCustomException extends RuntimeException {
	
	/* Classe para tratar exce��es de viola��o da integridade dos dados como em uma tentativa de 
	 * exclus�o de um dado que tenha relacionamento*/

	private static final long serialVersionUID = 1L;

	public DataIntegrityViolationCustomException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataIntegrityViolationCustomException(String message) {
		super(message);
	}

}
