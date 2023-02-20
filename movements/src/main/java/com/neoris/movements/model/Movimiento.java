package com.neoris.movements.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "MOVIMIENTOS", schema = "public")
public class Movimiento {

    @Id
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "tipo_movimiento")
    private Long tipoMovimiento;
    @Column(name = "saldo_inicial")
    private BigDecimal saldoInicial;
    @Column
    private BigDecimal saldo;
    @Column
    private BigDecimal valor;

}
