package com.pontoEAcesso.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Localidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    
    @Column
    @NotBlank(message = "O campo descrição deve ser informado!")
    @Length(min = 3, max = 150, message = "A descrição deve conter entre 3 e 150 caracteres.")
    private String descricao;
    
    @JsonIgnore
    @ManyToOne
    private NivelAcesso nivelAcesso;
}
