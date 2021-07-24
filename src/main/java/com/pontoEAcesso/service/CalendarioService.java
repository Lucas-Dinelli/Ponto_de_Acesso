package com.pontoEAcesso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.pontoEAcesso.model.Calendario;
import com.pontoEAcesso.repository.CalendarioRepository;
import com.pontoEAcesso.service.exceptions.DataIntegrityViolationCustomException;
import com.pontoEAcesso.service.exceptions.ObjectNotFoundException;

@Service
public class CalendarioService {
	
	@Autowired
	CalendarioRepository calendarioRepository;
	
	
	public Calendario save(Calendario calendario) {
		return this.calendarioRepository.save(calendario);
	}
	
	public List<Calendario> findAll(){
		return this.calendarioRepository.findAll();
	}
	
	public Calendario findById(Long id){
		Optional<Calendario> obj = calendarioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Nenhum calendário encontrado!"));
	}
	
	
	public Calendario update(Long id, Calendario calendarioAtualizado) {
		Calendario calendarioParaAtualizar = this.findById(id);
		updateObject(calendarioParaAtualizar, calendarioAtualizado);
		return this.calendarioRepository.save(calendarioParaAtualizar);
	}
	
	private void updateObject(Calendario calendarioParaAtualizar, Calendario calendarioAtualizado) {
		calendarioParaAtualizar.setDescricao(calendarioAtualizado.getDescricao());
		calendarioParaAtualizar.setDataEspecial(calendarioAtualizado.getDataEspecial());
	}
	
	
	public void delete(Long id) {
		findById(id);
		try {
			calendarioRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			System.out.println("Entrou");
			throw new DataIntegrityViolationCustomException("Este calendário não pode ser deletado "
					+ "pelo fato de possuir associações!");
		}
	}

}
