package com.neoris.movements.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.movements.dto.MovimientoDTO;
import com.neoris.movements.mapper.IMovimientoMapper;
import com.neoris.movements.model.Cuenta;
import com.neoris.movements.model.Movimiento;
import com.neoris.movements.model.TipoTransaccion;
import com.neoris.movements.repository.ICuentaRepository;
import com.neoris.movements.repository.IMovimientoRepository;
import com.neoris.movements.repository.ITipoTransaccionRepository;
import com.neoris.movements.service.IEventTransactionService;
import com.neoris.movements.service.IMovimientoService;
import com.neoris.movements.util.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

@Service
public class MovimientoServiceImpl implements IMovimientoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovimientoServiceImpl.class);
    private final IMovimientoRepository movimientoRepository;
    private final IEventTransactionService eventTransactionService;
    private final ICuentaRepository cuentaRepository;
    private final ITipoTransaccionRepository tipoTransaccionRepository;
    private final Map<String, BiFunction<MovimientoDTO, TipoTransaccion, Movimiento>> operaciones = new HashMap<>();

    @Autowired
    public MovimientoServiceImpl(IMovimientoRepository movimientoRepository,
                                 IEventTransactionService eventTransactionService,
                                 ICuentaRepository cuentaRepository, ITipoTransaccionRepository tipoTransaccionRepository) {
        this.movimientoRepository = movimientoRepository;
        this.eventTransactionService = eventTransactionService;
        this.cuentaRepository = cuentaRepository;
        this.tipoTransaccionRepository = tipoTransaccionRepository;

        // establecer las operaciones para cada tipo de movimiento
        operaciones.put(Constantes.SIGLA_DEPOSITO, this::realizarDeposito);
        operaciones.put(Constantes.SIGLA_RETIRO, this::realizarRetiro);
    }


    /**
     * Orquestador de la operacion, usa intefacer funcionales para determinar que operacion realizar
     * Las operaciones son determinadas por el idTipoMovimiento
     */
    @Override
    @Transactional
    public Movimiento operarMovimiento(MovimientoDTO movimientoDTO) {
        try {
            TipoTransaccion tipoTransaccion = this.tipoTransaccionRepository
                    .findTipoMovimientoById(movimientoDTO.getIdTipoMovimiento()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            // verificar que no haya especificado id para la transaccion
            if (movimientoDTO.getId() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No debe especificar id");
            }

            BiFunction<MovimientoDTO, TipoTransaccion, Movimiento> consumer = operaciones.get(tipoTransaccion.getSigla());
            return consumer.apply(movimientoDTO, tipoTransaccion);
        } catch (Exception e) {
            LOGGER.error("{} : {}", "Error al operar la transaccion", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Movimiento realizarRetiro(MovimientoDTO movimientoDTO, TipoTransaccion tipoTransaccion) {
        Cuenta cuenta = this.cuentaRepository.findCuentaByNumeroCuentaLike(movimientoDTO.getNumeroCuenta());
        BigDecimal valor = movimientoDTO.getValor();
        movimientoDTO.setValor(valor.negate());
        if (cuenta.getSaldo().subtract(valor).compareTo(BigDecimal.ZERO) < 0) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return registrarMovimiento(movimientoDTO, tipoTransaccion, valor, BigDecimal::subtract);
    }

    public Movimiento realizarDeposito(MovimientoDTO movimientoDTO, TipoTransaccion tipoTransaccion) {
        BigDecimal valor = movimientoDTO.getValor();
        return registrarMovimiento(movimientoDTO, tipoTransaccion, valor, BigDecimal::add);
    }


    private Movimiento registrarMovimiento(
            MovimientoDTO movimientoDTO,
            TipoTransaccion tipoTransaccion,
            BigDecimal valor,
            BinaryOperator<BigDecimal> operacion) {

        try {

            // Obtener la cuenta asociada al movimiento
            Cuenta cuenta = this.cuentaRepository.findCuentaByNumeroCuentaLike(movimientoDTO.getNumeroCuenta());

            // Actualizar el saldo de la cuenta
            movimientoDTO.setSaldoInicial(cuenta.getSaldo());

            // Registrar el Movimiento
            BigDecimal nuevoSaldo = operacion.apply(cuenta.getSaldo(), valor);
            cuenta.setSaldo(nuevoSaldo);
            movimientoDTO.setSaldo(nuevoSaldo);
            movimientoDTO.setValor(movimientoDTO.getValor());
            movimientoDTO.setFecha(new Date());
            this.cuentaRepository.guardarCuenta(cuenta);
            ObjectMapper objectMapper = new ObjectMapper();
            this.eventTransactionService.sendJson("Actualizacion_saldo", objectMapper.writeValueAsString(cuenta));
            LOGGER.info("Se ha modificado el saldo de la cuenta: {}", cuenta.getId());
            Movimiento movimiento = IMovimientoMapper.INSTANCE.toMovimiento(movimientoDTO);
            movimiento.setIdCuenta(cuenta.getId());
            movimiento.setCuenta(cuenta);
            movimiento.setTipoTransaccion(tipoTransaccion);
            return this.movimientoRepository.guardarMovimiento(movimiento);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
