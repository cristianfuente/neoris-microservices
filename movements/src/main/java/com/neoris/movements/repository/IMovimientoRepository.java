package com.neoris.movements.repository;

import com.neoris.movements.model.Movimiento;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovimientoRepository {
    Movimiento guardarMovimiento(Movimiento movimiento);
}
