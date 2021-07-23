package com.pda.pontoDeAcesso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pda.pontoDeAcesso.model.Localidade;
import com.pda.pontoDeAcesso.repository.LocalidadeRepository;
import com.pda.pontoDeAcesso.service.exceptions.ObjectNotFoundException;

@Service
public class LocalidadeService {
	
	@Autowired
	private LocalidadeRepository localidadeRepository;
	
	@Autowired
	private NivelAcessoService nivelAcessoService;
	
	
	public Localidade save(Long idNivelAcesso, Localidade localidade) {
		localidade.setNivelAcesso(nivelAcessoService.findById(idNivelAcesso));
		return this.localidadeRepository.save(localidade);
	}
	
	public List<Localidade> findAll(){
		return this.localidadeRepository.findAll();
	}
	
	public Localidade findById(Long id){
		Optional<Localidade> obj = localidadeRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Nenhuma localidade encontrada!"));
	}
	
	
	public List<Localidade> findAllByNivelAcesso(Long idNivel) {
		nivelAcessoService.findById(idNivel);
		return localidadeRepository.findAllByNivelAcesso(idNivel);
	}
	
	
	public Localidade update(Long id, Localidade localidadeAtualizada) {
		Localidade localidadeParaAtualizar = this.findById(id);
		updateObject(localidadeParaAtualizar, localidadeAtualizada);
		return this.localidadeRepository.save(localidadeParaAtualizar);
	}
	
	private void updateObject(Localidade localidadeParaAtualizar, Localidade localidadeAtualizada) {
		localidadeParaAtualizar.setDescricao(localidadeAtualizada.getDescricao());
	}
	
	public void delete(Long id) {
		findById(id);
		localidadeRepository.deleteById(id);
	}

}
