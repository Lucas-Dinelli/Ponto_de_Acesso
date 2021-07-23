package com.pda.pontoDeAcesso.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.pda.pontoDeAcesso.model.Calendario;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CalendarioDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private Long id;
    
    private String descricao;
    
    private LocalDate dataEspecial;
    
    public CalendarioDTO(Calendario calendario) {
    	setId(calendario.getId());
    	setDescricao(calendario.getDescricao());
    	setDataEspecial(calendario.getDataEspecial());
    }

}
