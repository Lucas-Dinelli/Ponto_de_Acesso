package com.pontoEAcesso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pontoEAcesso.model.JornadaDeTrabalho;
import com.pontoEAcesso.repository.JornadaDeTrabalhoRepository;
import com.pontoEAcesso.service.exceptions.ObjectNotFoundException;

@Service
public class JornadaDeTrabalhoService {
	
	@Autowired
	JornadaDeTrabalhoRepository jornadaDeTrabalhoRepository;
	
	
	public JornadaDeTrabalho save(JornadaDeTrabalho jornadaDeTrabalho) {
		return this.jornadaDeTrabalhoRepository.save(jornadaDeTrabalho);
	}
	
	public List<JornadaDeTrabalho> findAll(){
		return this.jornadaDeTrabalhoRepository.findAll();
	}
	
	public JornadaDeTrabalho findById(Long id){
		Optional<JornadaDeTrabalho> obj = jornadaDeTrabalhoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Nenhuma jornada de trabalho encontrada!"));
	}
	
	
	public JornadaDeTrabalho update(Long id, JornadaDeTrabalho jornadaAtualizada) {
		JornadaDeTrabalho jornadaParaAtualizar = this.findById(id);
		updateObject(jornadaParaAtualizar, jornadaAtualizada);
		return this.jornadaDeTrabalhoRepository.save(jornadaParaAtualizar);
	}
	
	private void updateObject(JornadaDeTrabalho jornadaParaAtualizar, JornadaDeTrabalho jornadaAtualizada) {
		jornadaParaAtualizar.setDescricao(jornadaAtualizada.getDescricao());
	}
	
	
	public void delete(Long id) {
		findById(id);
		jornadaDeTrabalhoRepository.deleteById(id);
	}
	
}
