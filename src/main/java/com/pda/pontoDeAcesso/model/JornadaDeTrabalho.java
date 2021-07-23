package com.pda.pontoDeAcesso.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

//@Audited

@Entity
public class JornadaDeTrabalho {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	@NotBlank(message = "O campo descrição deve ser informado!")
	@Length(min = 3, max = 150, message = "A descrição deve conter entre 3 e 100 caracteres.")
	private String descricao;
	
	@OneToMany(mappedBy = "jornadaDeTrabalho")
	private List<Usuario> usuarios;

}
