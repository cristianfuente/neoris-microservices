package com.neoris.movements.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss")
    private Date fecha;
    @Column(name = "id_tipo_movimiento")
    private Long idTipoMovimiento;
    @Column(name = "id_cuenta")
    private Long idCuenta;
    @Column(name = "saldo_inicial")
    private BigDecimal saldoInicial;
    @Column
    private BigDecimal saldo;
    @Column
    private BigDecimal valor;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cuenta", insertable = false, updatable = false)
    private Cuenta cuenta;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_movimiento", insertable = false, updatable = false)
    private TipoTransaccion tipoTransaccion;

}
