package com.pda.pontoDeAcesso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pda.pontoDeAcesso.model.Calendario;

public interface CalendarioRepository extends JpaRepository<Calendario, Long> {
	
}
