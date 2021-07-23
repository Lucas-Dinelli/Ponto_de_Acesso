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
public class CategoriaUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column
    @NotBlank(message = "O campo descricao deve ser informado!")
    @Length(min = 3, max = 150, message = "A descrição deve conter entre 3 e 150 caracteres.")
    private String descricao;
    
    @OneToMany(mappedBy = "categoriaUsuario")
    private List<Usuario> usuarios;
}

