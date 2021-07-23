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

import com.pda.pontoDeAcesso.dtos.CategoriaUsuarioDTO;
import com.pda.pontoDeAcesso.model.CategoriaUsuario;
import com.pda.pontoDeAcesso.service.CategoriaUsuarioService;


@RestController
@RequestMapping("/categoria-usuario")
public class CategoriaUsuarioController {
	
	@Autowired
	CategoriaUsuarioService categoriaUsuarioService;
	
	@PostMapping
	public ResponseEntity<CategoriaUsuario> create(@Valid @RequestBody CategoriaUsuario categoriaUsuario) {
		categoriaUsuario = categoriaUsuarioService.save(categoriaUsuario);
		// Para passar ao usuário uma URI de acesso ao novo objeto criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(categoriaUsuario.getId()).toUri();
		return ResponseEntity.created(uri).body(categoriaUsuario);
	}
	
	
	@GetMapping
	public ResponseEntity<List<CategoriaUsuarioDTO>> findAll() {
		List<CategoriaUsuario> categoriasUsuario = categoriaUsuarioService.findAll();
		List<CategoriaUsuarioDTO> categoriasUsuarioDTO = categoriasUsuario.stream()
				.map(categoriaUsuario -> new CategoriaUsuarioDTO(categoriaUsuario)).collect(Collectors.toList());
		return ResponseEntity.ok().body(categoriasUsuarioDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaUsuario> findById(@PathVariable Long id) {
		CategoriaUsuario obj = categoriaUsuarioService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoriaUsuario> update(@PathVariable Long id, @Valid @RequestBody CategoriaUsuario categoriaUsuarioAtualizado) {
		CategoriaUsuario novoCategoriaUsuario = categoriaUsuarioService.update(id, categoriaUsuarioAtualizado);
		return ResponseEntity.ok().body(novoCategoriaUsuario);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		categoriaUsuarioService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
