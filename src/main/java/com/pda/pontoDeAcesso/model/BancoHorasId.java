package com.pda.pontoDeAcesso.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class BancoHorasId implements Serializable {
	
	/**
	 * Classe para representar a chave primária composta de Banco Horas...
	*/
	
	private static final long serialVersionUID = 1L;
	
	private long idUsuario;
	
	private long idMovimentacao;


}
