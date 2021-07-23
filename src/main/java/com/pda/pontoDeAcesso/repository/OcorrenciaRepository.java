package com.pda.pontoDeAcesso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pda.pontoDeAcesso.model.Ocorrencia;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {
	
}
