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

    private static final long serialVersionUID = 6084878061633158510L;
    private Long id;
    private Date fecha;
    private Long idTipoMovimiento;
    private String tipoMovimiento;
    private BigDecimal saldoInicial;
    private BigDecimal saldo;
    private BigDecimal valor;
    private String numeroCuenta;
    private String nombreCliente;
    private String tipoCuenta;
    private Boolean estadoCuenta;

}
