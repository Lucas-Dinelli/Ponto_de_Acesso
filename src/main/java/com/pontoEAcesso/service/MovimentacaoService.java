package com.pontoEAcesso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pontoEAcesso.model.Movimentacao;
import com.pontoEAcesso.repository.MovimentacaoRepository;
import com.pontoEAcesso.service.exceptions.ObjectNotFoundException;

@Service
public class MovimentacaoService {
	
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private OcorrenciaService ocorrenciaService;
	
	@Autowired
	private CalendarioService calendarioService;
	
	
	public Movimentacao save(Long idUsuario, Long idOcorrencia, Long idCalendario, Movimentacao movimentacao) {
		movimentacao.setUsuario(usuarioService.findById(idUsuario));
		movimentacao.setOcorrencia(ocorrenciaService.findById(idOcorrencia));
		movimentacao.setCalendario(calendarioService.findById(idCalendario));
		return this.movimentacaoRepository.save(movimentacao);
	}
	
	public List<Movimentacao> findAll(){
		return this.movimentacaoRepository.findAll();
	}
	
	public Movimentacao findById(Long id){
		Optional<Movimentacao> obj = movimentacaoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Nenhuma movimentação encontrada!"));
	}
	
	
	public Movimentacao update(Long id, Movimentacao movimentacaoAtualizada) {
		Movimentacao movimentacaoParaAtualizar = this.findById(id);
		updateObject(movimentacaoParaAtualizar, movimentacaoAtualizada);
		return this.movimentacaoRepository.save(movimentacaoParaAtualizar);
	}
	
	private void updateObject(Movimentacao movimentacaoParaAtualizar, Movimentacao movimentacaoAtualizada) {
		movimentacaoParaAtualizar.setDataEntrada(movimentacaoAtualizada.getDataEntrada());
		movimentacaoParaAtualizar.setDataSaida(movimentacaoAtualizada.getDataSaida());
		movimentacaoParaAtualizar.setPeriodo(movimentacaoAtualizada.getPeriodo());
	}
	
	public void delete(Long id) {
		findById(id);
		movimentacaoRepository.deleteById(id);
	}

}
