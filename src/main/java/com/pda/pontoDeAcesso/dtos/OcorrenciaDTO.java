package com.pda.pontoDeAcesso.dtos;

import java.io.Serializable;

import com.pda.pontoDeAcesso.model.Ocorrencia;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OcorrenciaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long id;
    
    private String nome;
    
    private String descricao;
    
    public OcorrenciaDTO(Ocorrencia ocorrencia) {
    	setId(ocorrencia.getId());
    	setNome(ocorrencia.getNome());
    	setDescricao(ocorrencia.getDescricao());
    }

}
