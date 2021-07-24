package com.pontoEAcesso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.pontoEAcesso.model.Empresa;
import com.pontoEAcesso.repository.EmpresaRepository;
import com.pontoEAcesso.service.exceptions.DataIntegrityViolationCustomException;
import com.pontoEAcesso.service.exceptions.ObjectNotFoundException;

@Service
public class EmpresaService {
	
	@Autowired
	EmpresaRepository empresaRepository;
	
	
	public Empresa save(Empresa empresa) {
		return this.empresaRepository.save(empresa);
	}
	
	public List<Empresa> findAll(){
		return this.empresaRepository.findAll();
	}
	
	public Empresa findById(Long id){
		Optional<Empresa> obj = empresaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Nenhuma empresa encontrada!"));
	}
	
	
	public Empresa update(Long id, Empresa empresaAtualizada) {
		Empresa empresaParaAtualizar = this.findById(id);
		updateObject(empresaParaAtualizar, empresaAtualizada);
		return this.empresaRepository.save(empresaParaAtualizar);
	}
	
	private void updateObject(Empresa empresaParaAtualizar, Empresa empresaAtualizada) {
		empresaParaAtualizar.setDescricao(empresaAtualizada.getDescricao());
	}
	
	
	public void delete(Long id) {
		findById(id);
		try {
			empresaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			System.out.println("Entrou");
			throw new DataIntegrityViolationCustomException("Esta empresa não pode ser deletada "
					+ "pelo fato de possuir associações!");
		}
	}

}
