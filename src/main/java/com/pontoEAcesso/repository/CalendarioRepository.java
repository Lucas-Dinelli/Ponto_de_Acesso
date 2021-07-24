package com.pontoEAcesso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pontoEAcesso.model.Calendario;

public interface CalendarioRepository extends JpaRepository<Calendario, Long> {
	
}
