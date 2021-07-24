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

import com.pontoEAcesso.model.Movimentacao;
import com.pontoEAcesso.service.MovimentacaoService;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {
	
	@Autowired
	MovimentacaoService movimentacaoService;
	
	@PostMapping
	public ResponseEntity<Movimentacao> create(@RequestParam(value = "usuario", defaultValue = "0") Long idUsuario,
			@RequestParam(value = "ocorrencia", defaultValue = "0") Long idOcorrencia, 
			@RequestParam(value = "calendario", defaultValue = "0") Long idCalendario,
			@Valid @RequestBody Movimentacao movimentacao) {
		movimentacao = movimentacaoService.save(idUsuario, idOcorrencia, idCalendario, movimentacao);
		// Para passar ao usuário uma URI de acesso ao novo objeto criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(movimentacao.getId()).toUri();
		return ResponseEntity.created(uri).body(movimentacao);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Movimentacao> findById(@PathVariable Long id) {
		Movimentacao obj = movimentacaoService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<Movimentacao>> findAll() {
		return ResponseEntity.ok().body(movimentacaoService.findAll());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Movimentacao> update(@PathVariable Long id, @Valid @RequestBody Movimentacao movimentacaoAtualizada) {
		Movimentacao novaMovimentacao = movimentacaoService.update(id,movimentacaoAtualizada);
		return ResponseEntity.ok().body(novaMovimentacao);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		movimentacaoService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
