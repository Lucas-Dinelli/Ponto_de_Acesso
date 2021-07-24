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

import com.pontoEAcesso.dtos.OcorrenciaDTO;
import com.pontoEAcesso.model.Ocorrencia;
import com.pontoEAcesso.service.OcorrenciaService;

@RestController
@RequestMapping("/ocorrencia")
public class OcorrenciaController {
	
	@Autowired
	OcorrenciaService ocorrenciaService;
	
	@PostMapping
	public ResponseEntity<Ocorrencia> create(@Valid @RequestBody Ocorrencia ocorrencia) {
		ocorrencia = ocorrenciaService.save(ocorrencia);
		// Para passar ao usuário uma URI de acesso ao novo objeto criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(ocorrencia.getId()).toUri();
		return ResponseEntity.created(uri).body(ocorrencia);
	}
	
	
	@GetMapping
	public ResponseEntity<List<OcorrenciaDTO>> findAll() {
		List<Ocorrencia> ocorrencias = ocorrenciaService.findAll();
		List<OcorrenciaDTO> ocorrenciasDTO = ocorrencias.stream()
				.map(ocorrencia -> new OcorrenciaDTO(ocorrencia)).collect(Collectors.toList());
		return ResponseEntity.ok().body(ocorrenciasDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Ocorrencia> findById(@PathVariable Long id) {
		Ocorrencia obj = ocorrenciaService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Ocorrencia> update(@PathVariable Long id, @Valid @RequestBody Ocorrencia ocorrenciaAtualizada) {
		Ocorrencia novaOcorrencia = ocorrenciaService.update(id, ocorrenciaAtualizada);
		return ResponseEntity.ok().body(novaOcorrencia);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		ocorrenciaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
