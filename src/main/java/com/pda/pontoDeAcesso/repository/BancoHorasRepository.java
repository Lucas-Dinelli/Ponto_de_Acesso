package com.pda.pontoDeAcesso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pda.pontoDeAcesso.model.BancoHoras;
import com.pda.pontoDeAcesso.model.BancoHorasId;

public interface BancoHorasRepository extends JpaRepository<BancoHoras, BancoHorasId> {
	
	//CRUD is included

}
