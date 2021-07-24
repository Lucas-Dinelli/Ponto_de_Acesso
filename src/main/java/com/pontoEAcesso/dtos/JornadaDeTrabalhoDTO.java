package com.pontoEAcesso.dtos;

import java.io.Serializable;

import com.pontoEAcesso.model.JornadaDeTrabalho;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JornadaDeTrabalhoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long id;
	
	private String descricao;
	
	public JornadaDeTrabalhoDTO(JornadaDeTrabalho jornadaDeTrabalho) {
		setId(jornadaDeTrabalho.getId());
		setDescricao(jornadaDeTrabalho.getDescricao());
	}
	
}
