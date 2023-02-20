package com.neoris.movements.service.impl;

import com.neoris.movements.dto.MovimientoDTO;
import com.neoris.movements.mapper.IMovimientoMapper;
import com.neoris.movements.model.Cuenta;
import com.neoris.movements.model.Movimiento;
import com.neoris.movements.repository.ICuentaRepository;
import com.neoris.movements.repository.IMovimientoRepository;
import com.neoris.movements.service.IEventTransactionService;
import com.neoris.movements.service.IMovimientoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

public class MovimientoServiceImpl implements IMovimientoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovimientoServiceImpl.class);
    private final IMovimientoRepository movimientoRepository;
    private final IEventTransactionService eventTransactionService;
    private final ICuentaRepository cuentaRepository;

    @Autowired
    public MovimientoServiceImpl(IMovimientoRepository movimientoRepository,
                                 IEventTransactionService eventTransactionService,
                                 ICuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.eventTransactionService = eventTransactionService;
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    @Transactional
    public Movimiento registrarMovimiento(MovimientoDTO movimientoDTO){
        try {
            Cuenta cuenta = this.cuentaRepository.findCuentaByNumeroCuentaLike(movimientoDTO.getNumeroCuenta());
            this.cuentaRepository.findCuentaById(cuenta.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            Movimiento movimiento = IMovimientoMapper.INSTANCE.toMovimiento(movimientoDTO);
            return this.movimientoRepository.guardarMovimiento(movimiento);
        } catch (Exception e){
            LOGGER.error("Error movimient de cuenta: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error movimiento de cuenta");
        }
    }

}
