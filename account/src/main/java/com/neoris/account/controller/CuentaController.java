package com.neoris.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.account.dto.CuentaDTO;
import com.neoris.account.mapper.ICuentaMapper;
import com.neoris.account.service.ICuentaService;
import com.neoris.account.service.IEventCuentaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CuentaController.class);
    private final ICuentaService cuentaService;
    private final IEventCuentaService eventCuentaService;

    @Autowired
    public CuentaController(@Qualifier("cuentaServiceImpl") ICuentaService cuentaService,
                            @Qualifier("eventCuentaKafkaServiceImpl") IEventCuentaService eventCuentaService) {
        this.cuentaService = cuentaService;
        this.eventCuentaService = eventCuentaService;
    }

    @PostMapping
    public ResponseEntity<CuentaDTO> crearCliente(@Valid @RequestBody CuentaDTO cuentaDTO) {
        try {
            CuentaDTO cuentaCreadaDTO = ICuentaMapper.INSTANCE.toCuentaDTO(this.cuentaService.crearCuenta(cuentaDTO));
            ObjectMapper objectMapper = new ObjectMapper();
            this.eventCuentaService.sendJson("cuenta_creada" , objectMapper.writeValueAsString(cuentaCreadaDTO));
            return new ResponseEntity<>(cuentaCreadaDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error("Error inesperado {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error");
        }
    }


}
