package com.pda.pontoDeAcesso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pda.pontoDeAcesso.model.Localidade;

public interface LocalidadeRepository extends JpaRepository<Localidade, Long> {
	
	//CRUD is included
	
	@Query("SELECT l FROM Localidade l WHERE l.nivelAcesso.id = :idNivel")
	List<Localidade> findAllByNivelAcesso(@Param(value = "idNivel") Long idNivel);

}
