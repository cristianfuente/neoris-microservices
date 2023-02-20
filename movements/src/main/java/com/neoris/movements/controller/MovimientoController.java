package com.neoris.movements.controller;

import com.neoris.movements.dto.MovimientoDTO;
import com.neoris.movements.mapper.IMovimientoMapper;
import com.neoris.movements.service.IMovimientoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovimientoController.class);
    private final IMovimientoService movimientoService;

    @Autowired
    public MovimientoController(IMovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    public ResponseEntity<MovimientoDTO> registrarMovimiento(@Valid @RequestBody MovimientoDTO movimientoDTO) {
        MovimientoDTO movimientoRegistrado = IMovimientoMapper.INSTANCE
                .toMovimientoDTO(this.movimientoService.operarMovimiento(movimientoDTO));
        return new ResponseEntity<>(movimientoRegistrado, HttpStatus.OK);
    }


}
