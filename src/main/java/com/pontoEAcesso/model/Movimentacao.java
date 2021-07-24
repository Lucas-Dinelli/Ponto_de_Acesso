package com.pontoEAcesso.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Movimentacao {
	
	@Id
	//@EmbeddedId
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
        
	@Column
	//@NotBlank(message = "O campo data entrada deve ser informado!")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	@NotNull(message = "O campo data entrada deve ser informado no seguinte formato: dd-MM-yyyy HH:mm")
	private LocalDateTime dataEntrada;
        
	@Column
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime dataSaida;
        
	@Column
	@NotNull(message = "O campo periodo deve ser informado no seguinte formato: 0.00")
	private BigDecimal periodo;
	
	@JsonIgnore
	@ManyToOne
	private Usuario usuario;
    
	@JsonIgnore
	@ManyToOne
	private Ocorrencia ocorrencia;
    
	@JsonIgnore
	@ManyToOne
	private Calendario calendario;
}
