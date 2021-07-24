package com.pontoEAcesso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pontoEAcesso.model.BancoHoras;
import com.pontoEAcesso.model.BancoHorasId;

public interface BancoHorasRepository extends JpaRepository<BancoHoras, BancoHorasId> {
	
	//CRUD is included

}
