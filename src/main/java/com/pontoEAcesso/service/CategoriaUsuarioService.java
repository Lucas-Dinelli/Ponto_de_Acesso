package com.pontoEAcesso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.pontoEAcesso.model.CategoriaUsuario;
import com.pontoEAcesso.repository.CategoriaUsuarioRepository;
import com.pontoEAcesso.service.exceptions.DataIntegrityViolationCustomException;
import com.pontoEAcesso.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaUsuarioService {
	
	@Autowired
	CategoriaUsuarioRepository categoriaUsuarioRepository;
	
	
	public CategoriaUsuario save(CategoriaUsuario categoriaUsuario) {
		return this.categoriaUsuarioRepository.save(categoriaUsuario);
	}
	
	public List<CategoriaUsuario> findAll(){
		return this.categoriaUsuarioRepository.findAll();
	}
	
	public CategoriaUsuario findById(Long id){
		Optional<CategoriaUsuario> obj = categoriaUsuarioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Nenhuma categoria de usuário encontrada!"));
	}
	
	
	public CategoriaUsuario update(Long id, CategoriaUsuario categoriaUsuarioAtualizada) {
		CategoriaUsuario categoriaUsuarioParaAtualizar = this.findById(id);
		updateObject(categoriaUsuarioParaAtualizar, categoriaUsuarioAtualizada);
		return this.categoriaUsuarioRepository.save(categoriaUsuarioParaAtualizar);
	}
	
	private void updateObject(CategoriaUsuario categoriaUsuarioParaAtualizar, CategoriaUsuario categoriaUsuarioAtualizado) {
		categoriaUsuarioParaAtualizar.setDescricao(categoriaUsuarioAtualizado.getDescricao());
	}
	
	
	public void delete(Long id) {
		findById(id);
		try {
			categoriaUsuarioRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			System.out.println("Entrou");
			throw new DataIntegrityViolationCustomException("Esta categoria de usuário não pode ser deletada "
					+ "pelo fato de possuir associações!");
		}
	}

}
