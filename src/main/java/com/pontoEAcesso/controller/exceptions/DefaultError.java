package com.pontoEAcesso.controller.exceptions;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultError {
	
	private LocalDate dataAtual;
	private Integer statusDoErro;
	private String mensagem;
	
}
