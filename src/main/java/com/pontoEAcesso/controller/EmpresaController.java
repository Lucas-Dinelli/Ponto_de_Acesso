package com.pontoEAcesso.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pontoEAcesso.dtos.EmpresaDTO;
import com.pontoEAcesso.model.Empresa;
import com.pontoEAcesso.service.EmpresaService;


@RestController
@RequestMapping("/empresa")
public class EmpresaController {
	
	@Autowired
	EmpresaService empresaService;
	
	@PostMapping
	public ResponseEntity<Empresa> create(@Valid @RequestBody Empresa empresa) {
		empresa = empresaService.save(empresa);
		// Para passar ao usuário uma URI de acesso ao novo objeto criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(empresa.getId()).toUri();
		return ResponseEntity.created(uri).body(empresa);
	}
	
	
	@GetMapping
	public ResponseEntity<List<EmpresaDTO>> findAll() {
		List<Empresa> empresas = empresaService.findAll();
		List<EmpresaDTO> empresasDTO = empresas.stream()
				.map(empresa -> new EmpresaDTO(empresa)).collect(Collectors.toList());
		return ResponseEntity.ok().body(empresasDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Empresa> findById(@PathVariable Long id) {
		Empresa obj = empresaService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Empresa> update(@PathVariable Long id, @Valid @RequestBody Empresa empresaAtualizada) {
		Empresa novaEmpresa = empresaService.update(id, empresaAtualizada);
		return ResponseEntity.ok().body(novaEmpresa);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		empresaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
