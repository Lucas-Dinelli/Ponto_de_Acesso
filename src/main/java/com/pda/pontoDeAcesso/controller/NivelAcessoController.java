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

import com.pda.pontoDeAcesso.dtos.NivelAcessoDTO;
import com.pda.pontoDeAcesso.model.NivelAcesso;
import com.pda.pontoDeAcesso.service.NivelAcessoService;


@RestController
@RequestMapping("/nivel-acesso")
public class NivelAcessoController {
	
	@Autowired
	NivelAcessoService nivelAcessoService;
	
	@PostMapping
	public ResponseEntity<NivelAcesso> create(@Valid @RequestBody NivelAcesso nivelAcesso) {
		nivelAcesso = nivelAcessoService.save(nivelAcesso);
		// Para passar ao usuário uma URI de acesso ao novo objeto criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(nivelAcesso.getId()).toUri();
		return ResponseEntity.created(uri).body(nivelAcesso);
	}
	
	
	@GetMapping
	public ResponseEntity<List<NivelAcessoDTO>> findAll() {
		List<NivelAcesso> niveisAcesso = nivelAcessoService.findAll();
		List<NivelAcessoDTO> niveisAcessoDTO = niveisAcesso.stream()
				.map(nivelAcesso -> new NivelAcessoDTO(nivelAcesso)).collect(Collectors.toList());
		return ResponseEntity.ok().body(niveisAcessoDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<NivelAcesso> findById(@PathVariable Long id) {
		NivelAcesso obj = nivelAcessoService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<NivelAcesso> update(@PathVariable Long id, @Valid @RequestBody NivelAcesso nivelAcessoAtualizado) {
		NivelAcesso novoNivelAcesso = nivelAcessoService.update(id, nivelAcessoAtualizado);
		return ResponseEntity.ok().body(novoNivelAcesso);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		nivelAcessoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
