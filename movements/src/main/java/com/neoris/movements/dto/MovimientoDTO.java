package com.neoris.movements.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class MovimientoDTO implements Serializable {

    private static final long serialVersionUID = -3086752229909122459L;
    private Long id;
    private Date fecha;
    private Long tipoMovimiento;
    private BigDecimal saldoInicial;
    private BigDecimal saldo;
    private BigDecimal valor;
    private String  numeroCuenta;

}
