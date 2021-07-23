package com.pda.pontoDeAcesso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pda.pontoDeAcesso.model.JornadaDeTrabalho;

@Repository
public interface JornadaDeTrabalhoRepository extends JpaRepository<JornadaDeTrabalho, Long> {
	
	// CRUD is included
	
}
