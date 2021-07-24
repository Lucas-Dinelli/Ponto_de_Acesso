package com.pontoEAcesso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pontoEAcesso.model.JornadaDeTrabalho;

@Repository
public interface JornadaDeTrabalhoRepository extends JpaRepository<JornadaDeTrabalho, Long> {
	
	// CRUD is included
	
}
