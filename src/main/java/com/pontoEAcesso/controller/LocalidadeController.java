package com.pontoEAcesso.controller;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pontoEAcesso.model.Localidade;
import com.pontoEAcesso.service.LocalidadeService;

@RestController
@RequestMapping("/localidade")
public class LocalidadeController {
	
	@Autowired
	LocalidadeService localidadeService;
	
	@PostMapping
	public ResponseEntity<Localidade> create(@RequestParam(value = "nivel-acesso", defaultValue = "0") Long idNivelAcesso, @Valid @RequestBody Localidade localidade) {
		localidade = localidadeService.save(idNivelAcesso, localidade);
		// Para passar ao usuário uma URI de acesso ao novo objeto criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(localidade.getId()).toUri();
		return ResponseEntity.created(uri).body(localidade);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Localidade> findById(@PathVariable Long id) {
		Localidade obj = localidadeService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<Localidade>> findAllByNivelAcesso(@RequestParam(value = "nivel-acesso", defaultValue = "0") Long idNivelAcesso) {
		return ResponseEntity.ok().body(localidadeService.findAllByNivelAcesso(idNivelAcesso));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Localidade> update(@PathVariable Long id, @Valid @RequestBody Localidade localidadeAtualizada) {
		Localidade novaLocalidade = localidadeService.update(id, localidadeAtualizada);
		return ResponseEntity.ok().body(novaLocalidade);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		localidadeService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
