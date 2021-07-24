package com.pontoEAcesso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pontoEAcesso.model.Usuario;
import com.pontoEAcesso.repository.UsuarioRepository;
import com.pontoEAcesso.service.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CategoriaUsuarioService categoriaUsuarioService;
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private NivelAcessoService nivelAcessoService;
	
	@Autowired
	private JornadaDeTrabalhoService jornadaDeTrabalhoService;
	
	
	public Usuario save(Long idCategoriaUsuario, Long idEmpresa, Long idNivelAcesso, Long idJornadaDeTrabalho, Usuario usuario) {
		usuario.setCategoriaUsuario(categoriaUsuarioService.findById(idCategoriaUsuario));
		usuario.setEmpresa(empresaService.findById(idEmpresa));
		usuario.setNivelAcesso(nivelAcessoService.findById(idNivelAcesso));
		usuario.setJornadaDeTrabalho(jornadaDeTrabalhoService.findById(idJornadaDeTrabalho));
		return this.usuarioRepository.save(usuario);
	}
	
	public List<Usuario> findAll(){
		return this.usuarioRepository.findAll();
	}
	
	public Usuario findById(Long id){
		Optional<Usuario> obj = usuarioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Nenhum usuario encontrado!"));
	}
	
	
	/*public List<Usuario> findAllByCategoriaUsuario(Long idCategoriaUsuario) {
		categoriaUsuarioService.findById(idCategoriaUsuario);
		return usuarioRepository.findAllByCategoriaUsuario(idCategoriaUsuario);
	}*/
	
	
	public Usuario update(Long id, Usuario usuarioAtualizado) {
		Usuario usuarioParaAtualizar = this.findById(id);
		updateObject(usuarioParaAtualizar, usuarioAtualizado);
		return this.usuarioRepository.save(usuarioParaAtualizar);
	}
	
	private void updateObject(Usuario usuarioParaAtualizar, Usuario usuarioAtualizado) {
		usuarioParaAtualizar.setNome(usuarioAtualizado.getNome());
		usuarioParaAtualizar.setTolerancia(usuarioAtualizado.getTolerancia());
		usuarioParaAtualizar.setInicioJornada(usuarioAtualizado.getInicioJornada());
		usuarioParaAtualizar.setFinalJornada(usuarioAtualizado.getFinalJornada());
	}
	
	public void delete(Long id) {
		findById(id);
		usuarioRepository.deleteById(id);
	}

}
