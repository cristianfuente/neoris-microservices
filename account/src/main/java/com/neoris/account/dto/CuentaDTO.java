package com.neoris.account.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CuentaDTO implements Serializable {

    private static final long serialVersionUID = -3086752229909122459L;
    private Long id;
    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldo;
    private Boolean estado;
    private Long idCliente;
    private String nombreCliente;

}
