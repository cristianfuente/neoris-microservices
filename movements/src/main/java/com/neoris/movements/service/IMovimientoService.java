package com.neoris.movements.service;

import com.neoris.movements.dto.MovimientoDTO;
import com.neoris.movements.model.Movimiento;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface IMovimientoService {

    @Transactional
    Movimiento operarMovimiento(MovimientoDTO movimientoDTO);
}
