package com.pontoEAcesso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pontoEAcesso.model.Ocorrencia;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {
	
}
