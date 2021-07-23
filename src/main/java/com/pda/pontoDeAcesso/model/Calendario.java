package com.pda.pontoDeAcesso.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Calendario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    @NotBlank(message = "O campo descricao deve ser informado!")
    @Length(min = 3, max = 150, message = "A descrição deve conter entre 3 e 150 caracteres.")
    private String descricao;
    
    @Column
    @NotNull(message = "O campo data especial deve ser informado no seguinte formato: dd-MM-yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataEspecial;
    
    @OneToMany(mappedBy = "calendario")
    private List<Movimentacao> movimentacoes;
}
