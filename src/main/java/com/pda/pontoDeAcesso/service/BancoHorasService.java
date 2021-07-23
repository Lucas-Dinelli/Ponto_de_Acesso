package com.pda.pontoDeAcesso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pda.pontoDeAcesso.model.BancoHoras;
import com.pda.pontoDeAcesso.model.BancoHorasId;
import com.pda.pontoDeAcesso.repository.BancoHorasRepository;
import com.pda.pontoDeAcesso.service.exceptions.ObjectNotFoundException;

@Service
public class BancoHorasService {
	
	@Autowired
	private BancoHorasRepository bancoHorasRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private MovimentacaoService movimentacaoService;
	
	
	public BancoHoras save(Long idUsuario, Long idMovimentacao, BancoHoras bancoHoras) {
		usuarioService.findById(idUsuario);
		movimentacaoService.findById(idMovimentacao);
		bancoHoras.setBancoHorasId(new BancoHorasId(idUsuario, idMovimentacao));
		return this.bancoHorasRepository.save(bancoHoras);
	}
	
	public List<BancoHoras> findAll(){
		return this.bancoHorasRepository.findAll();
	}
	
	public BancoHoras findById(Long idUsuario, Long idMovimentacao){
		Optional<BancoHoras> obj = bancoHorasRepository.findById(new BancoHorasId(idUsuario, idMovimentacao));
		return obj.orElseThrow(() -> new ObjectNotFoundException("Nenhum banco horas encontrado!"));
	}
	
	
	public BancoHoras update(Long idUsuario, Long idMovimentacao, BancoHoras bancoHorasAtualizado) {
		BancoHoras bancoHorasParaAtualizar = this.findById(idUsuario, idMovimentacao);
		updateObject(bancoHorasParaAtualizar, bancoHorasAtualizado);
		return this.bancoHorasRepository.save(bancoHorasParaAtualizar);
	}
	
	private void updateObject(BancoHoras bancoHorasParaAtualizar, BancoHoras bancoHorasAtualizado) {
		bancoHorasParaAtualizar.setDataTrabalhada(bancoHorasAtualizado.getDataTrabalhada());
		bancoHorasParaAtualizar.setQuantidadeHoras(bancoHorasAtualizado.getQuantidadeHoras());
		bancoHorasParaAtualizar.setSaldoHoras(bancoHorasAtualizado.getSaldoHoras());
	}
	
	public void delete(Long idUsuario, Long idMovimentacao) {
		findById(idUsuario, idMovimentacao);
		bancoHorasRepository.deleteById(new BancoHorasId(idUsuario, idMovimentacao));
	}

}
