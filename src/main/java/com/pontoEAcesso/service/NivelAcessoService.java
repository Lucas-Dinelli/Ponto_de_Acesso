package com.pontoEAcesso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.pontoEAcesso.model.NivelAcesso;
import com.pontoEAcesso.repository.NivelAcessoRepository;
import com.pontoEAcesso.service.exceptions.DataIntegrityViolationCustomException;
import com.pontoEAcesso.service.exceptions.ObjectNotFoundException;

@Service
public class NivelAcessoService {
	
	@Autowired
	NivelAcessoRepository nivelAcessoRepository;
	
	
	public NivelAcesso save(NivelAcesso nivelAcesso) {
		return this.nivelAcessoRepository.save(nivelAcesso);
	}
	
	public List<NivelAcesso> findAll(){
		return this.nivelAcessoRepository.findAll();
	}
	
	public NivelAcesso findById(Long id){
		Optional<NivelAcesso> obj = nivelAcessoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Nenhum nível de acesso encontrado!"));
	}
	
	
	public NivelAcesso update(Long id, NivelAcesso nivelAcessoAtualizado) {
		NivelAcesso nivelAcessoParaAtualizar = this.findById(id);
		updateObject(nivelAcessoParaAtualizar, nivelAcessoAtualizado);
		return this.nivelAcessoRepository.save(nivelAcessoParaAtualizar);
	}
	
	private void updateObject(NivelAcesso nivelAcessoParaAtualizar, NivelAcesso nivelAcessoAtualizado) {
		nivelAcessoParaAtualizar.setDescricao(nivelAcessoAtualizado.getDescricao());
	}
	
	
	public void delete(Long id) {
		findById(id);
		try {
			nivelAcessoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			System.out.println("Entrou");
			throw new DataIntegrityViolationCustomException("Este nível não pode ser deletado "
					+ "pelo fato de possuir associações!");
		}
	}

}
