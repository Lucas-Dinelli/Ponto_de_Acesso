package com.pda.pontoDeAcesso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pda.pontoDeAcesso.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}
