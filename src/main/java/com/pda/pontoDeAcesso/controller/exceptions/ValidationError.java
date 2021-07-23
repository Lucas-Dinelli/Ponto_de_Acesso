package com.pda.pontoDeAcesso.controller.exceptions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ValidationError extends DefaultError {
	
	private List<FieldValidation> listaDeValidacoesDeCampos = new ArrayList<>();
	
	public ValidationError(LocalDate dataAtual, Integer statusDoErro, String mensagem) {
		super(dataAtual, statusDoErro, mensagem);
	}
	
	public List<FieldValidation> getListaDeValidacoesDeCampos() {
		return listaDeValidacoesDeCampos;
	}

	public void addValidacaoDeCampos(String campo, String mensagem) {
		this.listaDeValidacoesDeCampos.add(new FieldValidation(campo, mensagem));
	}
	
}
