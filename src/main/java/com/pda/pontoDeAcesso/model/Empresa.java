package com.pda.pontoDeAcesso.model;

import lombok.*;
//import org.hibernate.envers.Audited;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

//@Audited

@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    @NotBlank(message = "O campo nome deve ser informado!")
    @Length(min = 3, max = 100, message = "O nome deve conter entre 3 e 100 caracteres.")
    private String nome;
    
    @Column
    @Length(min = 3, max = 150, message = "A descrição deve conter entre 3 e 150 caracteres.")
    private String descricao;
    
    @Column
    @NotBlank(message = "O campo CNPJ deve ser informado!")
    @Length(min = 14, max = 14, message = "O CNPJ deve conter 14 caracteres.")
    private String cnpj;
    
    @Column
    @NotBlank(message = "O campo endereço deve ser informado!")
    @Length(min = 3, max = 100, message = "O endereço deve conter entre 3 e 100 caracteres.")
    private String endereco;
    
    @Column
    @NotBlank(message = "O campo bairro deve ser informado!")
    private String bairro;
    
    @Column
    @NotBlank(message = "O campo cidade deve ser informado!")
    private String cidade;
    
    @Column
    @NotBlank(message = "O campo estado deve ser informado!")
    private String estado;
    
    @Column
    @NotBlank(message = "O campo telefone deve ser informado!")
    @Length(min = 8, max = 15, message = "O telefone deve conter entre 9 e 15 caracteres.")
    private String telefone;
    
    @OneToMany(mappedBy = "empresa")
    private List<Usuario> usuarios;
}
