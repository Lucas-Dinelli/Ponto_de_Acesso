package com.pontoEAcesso.model;

import lombok.*;
//import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

//@Audited

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    @NotBlank(message = "O campo nome deve ser informado!")
    @Length(min = 3, max = 100, message = "O nome deve conter entre 3 e 100 caracteres.")
    private String nome;
    
    @Column
    @NotNull(message = "O campo tolerancia deve ser informado no seguinte formato: 0.00")
    private BigDecimal tolerancia;
    
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    @NotNull(message = "O campo inicio jornada deve ser informado no seguinte formato: dd-MM-yyyy HH:mm")
    private LocalDateTime inicioJornada;
    
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime finalJornada;
    
    @JsonIgnore
    @ManyToOne
    private CategoriaUsuario categoriaUsuario;
    
    @JsonIgnore
    @ManyToOne
    private Empresa empresa;
    
    @JsonIgnore
    @ManyToOne
    private NivelAcesso nivelAcesso;
    
    @JsonIgnore
    @ManyToOne
    private JornadaDeTrabalho jornadaDeTrabalho;
    
    @OneToMany(mappedBy = "usuario")
    private List<Movimentacao> movimentacoes;
}

