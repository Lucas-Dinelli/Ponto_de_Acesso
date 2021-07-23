package com.pda.pontoDeAcesso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pda.pontoDeAcesso.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
	
	//CRUD is included

}
