package com.pda.pontoDeAcesso.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class BancoHoras {
    
    @EmbeddedId
    private BancoHorasId bancoHorasId;
    
    @Column
    @NotNull(message = "O campo data trabalhada deve ser informado no seguinte formato: dd-MM-yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataTrabalhada;
    
    @Column
    @NotNull(message = "O campo quantidade horas deve ser informado no seguinte formato: H.mm")
    private BigDecimal quantidadeHoras;
    
    @Column
    @NotNull(message = "O campo saldo horas deve ser informado no seguinte formato: H.mm")
    private BigDecimal saldoHoras;

}
