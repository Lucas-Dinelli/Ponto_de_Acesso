package com.pda.pontoDeAcesso.controller;

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

import com.pda.pontoDeAcesso.model.Usuario;
import com.pda.pontoDeAcesso.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<Usuario> create(@RequestParam(value = "categoria-usuario", defaultValue = "0") Long idCategoriaUsuario,
			@RequestParam(value = "empresa", defaultValue = "0") Long idEmpresa, 
			@RequestParam(value = "nivel-acesso", defaultValue = "0") Long idNivelAcesso,
			@RequestParam(value = "jornada-de-trabalho", defaultValue = "0") Long idJornadaDeTrabalho,
			@Valid @RequestBody Usuario usuario) {
		usuario = usuarioService.save(idCategoriaUsuario, idEmpresa, idNivelAcesso, idJornadaDeTrabalho, usuario);
		// Para passar ao usuário uma URI de acesso ao novo objeto criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(usuario);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Long id) {
		Usuario obj = usuarioService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> findAll() {
		return ResponseEntity.ok().body(usuarioService.findAll());
	}
	
	/*@GetMapping
	public ResponseEntity<List<Usuario>> findAllByCategoriaUsuario(@RequestParam(value = "categoria-usuario", defaultValue = "0") Long idCategoriaUsuario) {
		return ResponseEntity.ok().body(usuarioService.findAllByCategoriaUsuario(idCategoriaUsuario));
	}*/
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> update(@PathVariable Long id, @Valid @RequestBody Usuario usuarioAtualizado) {
		Usuario novoUsuario = usuarioService.update(id, usuarioAtualizado);
		return ResponseEntity.ok().body(novoUsuario);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		usuarioService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
