package com.pontoEAcesso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.pontoEAcesso.model.Ocorrencia;
import com.pontoEAcesso.repository.OcorrenciaRepository;
import com.pontoEAcesso.service.exceptions.DataIntegrityViolationCustomException;
import com.pontoEAcesso.service.exceptions.ObjectNotFoundException;

@Service
public class OcorrenciaService {
	
	@Autowired
	OcorrenciaRepository ocorrenciaRepository;
	
	
	public Ocorrencia save(Ocorrencia ocorrencia) {
		return this.ocorrenciaRepository.save(ocorrencia);
	}
	
	public List<Ocorrencia> findAll(){
		return this.ocorrenciaRepository.findAll();
	}
	
	public Ocorrencia findById(Long id){
		Optional<Ocorrencia> obj = ocorrenciaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Nenhuma ocorrência encontrada!"));
	}
	
	
	public Ocorrencia update(Long id, Ocorrencia ocorrenciaAtualizada) {
		Ocorrencia ocorrenciaParaAtualizar = this.findById(id);
		updateObject(ocorrenciaParaAtualizar, ocorrenciaAtualizada);
		return this.ocorrenciaRepository.save(ocorrenciaParaAtualizar);
	}
	
	private void updateObject(Ocorrencia ocorrenciaParaAtualizar, Ocorrencia ocorrenciaAtualizada) {
		ocorrenciaParaAtualizar.setNome(ocorrenciaAtualizada.getNome());
		ocorrenciaParaAtualizar.setDescricao(ocorrenciaAtualizada.getDescricao());
	}
	
	
	public void delete(Long id) {
		findById(id);
		try {
			ocorrenciaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			System.out.println("Entrou");
			throw new DataIntegrityViolationCustomException("Esta ocorrência não pode ser deletada "
					+ "pelo fato de possuir associações!");
		}
	}

}
