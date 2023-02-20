package com.neoris.movements.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CUENTA", schema = "public")
public class Cuenta {

    @Id
    private Long id;
    @Column(name = "numero_cuenta", unique = true)
    private String numeroCuenta;
    @Column(name = "tipo_cuenta")
    private String tipoCuenta;
    @Column
    private BigDecimal saldo;
    @Column
    private Boolean estado;
    @Column(name = "id_cliente")
    private Long idCliente;
    @Column(name = "nombre_cliente")
    private String nombreCliente;

}
