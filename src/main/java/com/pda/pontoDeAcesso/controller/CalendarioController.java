package com.pda.pontoDeAcesso.controller;

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

import com.pda.pontoDeAcesso.dtos.CalendarioDTO;
import com.pda.pontoDeAcesso.model.Calendario;
import com.pda.pontoDeAcesso.service.CalendarioService;


@RestController
@RequestMapping("/calendario")
public class CalendarioController {
	
	@Autowired
	CalendarioService calendarioService;
	
	@PostMapping
	public ResponseEntity<Calendario> create(@Valid @RequestBody Calendario calendario) {
		calendario = calendarioService.save(calendario);
		// Para passar ao usuário uma URI de acesso ao novo objeto criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(calendario.getId()).toUri();
		return ResponseEntity.created(uri).body(calendario);
	}
	
	
	@GetMapping
	public ResponseEntity<List<CalendarioDTO>> findAll() {
		List<Calendario> calendarios = calendarioService.findAll();
		List<CalendarioDTO> calendariosDTO = calendarios.stream()
				.map(calendario -> new CalendarioDTO(calendario)).collect(Collectors.toList());
		return ResponseEntity.ok().body(calendariosDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Calendario> findById(@PathVariable Long id) {
		Calendario obj = calendarioService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Calendario> update(@PathVariable Long id, @Valid @RequestBody Calendario calendarioAtualizado) {
		Calendario novoCalendario = calendarioService.update(id, calendarioAtualizado);
		return ResponseEntity.ok().body(novoCalendario);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		calendarioService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
