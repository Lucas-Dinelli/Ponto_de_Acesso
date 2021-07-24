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

import com.pontoEAcesso.model.BancoHoras;
import com.pontoEAcesso.service.BancoHorasService;

@RestController
@RequestMapping("/banco-horas")
public class BancoHorasController {
	
	@Autowired
	BancoHorasService bancoHorasService;
	
	@PostMapping
	public ResponseEntity<BancoHoras> create(@RequestParam(value = "usuario", defaultValue = "0") Long idUsuario,
			@RequestParam(value = "movimentacao", defaultValue = "0") Long idMovimentacao,
			@Valid @RequestBody BancoHoras bancoHoras) {
		bancoHoras = bancoHorasService.save(idUsuario, idMovimentacao, bancoHoras);
		// Para passar ao usuário uma URI de acesso ao novo objeto criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(bancoHoras.getBancoHorasId()).toUri();
		return ResponseEntity.created(uri).body(bancoHoras);
	}
	
	@GetMapping("/{idUsuario}/{idMovimentacao}")
	public ResponseEntity<BancoHoras> findById(@PathVariable Long idUsuario, @PathVariable Long idMovimentacao) {
		BancoHoras obj = bancoHorasService.findById(idUsuario, idMovimentacao);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<BancoHoras>> findAll() {
		return ResponseEntity.ok().body(bancoHorasService.findAll());
	}
	
	@PutMapping("/{idUsuario}/{idMovimentacao}")
	public ResponseEntity<BancoHoras> update(@PathVariable Long idUsuario, @PathVariable Long idMovimentacao, @Valid @RequestBody BancoHoras bancoHorasAtualizado) {
		BancoHoras novoBancoHoras = bancoHorasService.update(idUsuario, idMovimentacao, bancoHorasAtualizado);
		return ResponseEntity.ok().body(novoBancoHoras);
	}
	
	
	@DeleteMapping("/{idUsuario}/{idMovimentacao}")
	public ResponseEntity<Void> delete(@PathVariable Long idUsuario, @PathVariable Long idMovimentacao) {
		bancoHorasService.delete(idUsuario, idMovimentacao);
		return ResponseEntity.noContent().build();
	}

}
