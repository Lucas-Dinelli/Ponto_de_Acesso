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

import com.pontoEAcesso.dtos.JornadaDeTrabalhoDTO;
import com.pontoEAcesso.model.JornadaDeTrabalho;
import com.pontoEAcesso.service.JornadaDeTrabalhoService;

@RestController
@RequestMapping("/jornada-de-trabalho")
public class JornadaDeTrabalhoController {
	
	@Autowired
	JornadaDeTrabalhoService jornadaService;
	
	@PostMapping
	public ResponseEntity<JornadaDeTrabalho> create(@Valid @RequestBody JornadaDeTrabalho jornadaDeTrabalho) {
		jornadaDeTrabalho = jornadaService.save(jornadaDeTrabalho);
		// Para passar ao usuário uma URI de acesso ao novo objeto criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(jornadaDeTrabalho.getId()).toUri();
		return ResponseEntity.created(uri).body(jornadaDeTrabalho);
	}
	
	@GetMapping
	public ResponseEntity<List<JornadaDeTrabalhoDTO>> findAll() {
		List<JornadaDeTrabalho> jornadasDeTrabalho = jornadaService.findAll();
		List<JornadaDeTrabalhoDTO> jornadasDTO = jornadasDeTrabalho.stream()
				.map(jornada -> new JornadaDeTrabalhoDTO(jornada)).collect(Collectors.toList());
		return ResponseEntity.ok().body(jornadasDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<JornadaDeTrabalho> findById(@PathVariable Long id) {
		JornadaDeTrabalho obj = jornadaService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<JornadaDeTrabalho> update(@PathVariable Long id, @Valid @RequestBody JornadaDeTrabalho jornadaAtualizada) {
		JornadaDeTrabalho novaJornada = jornadaService.update(id, jornadaAtualizada);
		return ResponseEntity.ok().body(novaJornada);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		jornadaService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
