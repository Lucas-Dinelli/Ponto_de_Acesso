package com.pontoEAcesso.model;

import lombok.*;

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

@Entity
public class Ocorrencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column
    @NotBlank(message = "O campo nome deve ser informado!")
    private String nome;
    
    @Column
    @NotBlank(message = "O campo descrição deve ser informado!")
    @Length(min = 3, max = 150, message = "A descrição deve conter entre 3 e 100 caracteres.")
    private String descricao;
    
    @OneToMany(mappedBy = "ocorrencia")
    private List<Movimentacao> movimentacoes;
}
