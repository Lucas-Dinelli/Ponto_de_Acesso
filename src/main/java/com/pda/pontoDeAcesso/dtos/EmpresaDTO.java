package com.pda.pontoDeAcesso.dtos;

import java.io.Serializable;

import com.pda.pontoDeAcesso.model.Empresa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmpresaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private Long id;
    
    private String nome;
    
    private String descricao;
    
    private String cnpj;
    
    private String endereco;
    
    private String bairro;
    
    private String cidade;
    
    private String estado;
    
    private String telefone;

	public EmpresaDTO(Empresa empresa) {
		setId(empresa.getId());
		setNome(empresa.getNome());
		setDescricao(empresa.getDescricao());
		setCnpj(empresa.getCnpj());
		setEndereco(empresa.getEndereco());
		setBairro(empresa.getBairro());
		setCidade(empresa.getCidade());
		setEstado(empresa.getEstado());
		setTelefone(empresa.getTelefone());
	}

}
