package com.pontoEAcesso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pontoEAcesso.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}
