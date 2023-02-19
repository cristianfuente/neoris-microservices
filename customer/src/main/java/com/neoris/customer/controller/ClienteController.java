package com.neoris.customer.controller;

import com.neoris.customer.dto.ClienteDTO;
import com.neoris.customer.event.IEventService;
import com.neoris.customer.service.IClienteService;
import com.neoris.customer.util.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);
    private final IClienteService clienteService;
    private final IEventService eventService;

    @Autowired
    public ClienteController(@Qualifier("clienteServiceImpl") IClienteService clienteService, @Qualifier("kafkaEventServiceImpl") IEventService eventService) {
        this.clienteService = clienteService;
        this.eventService = eventService;
    }

    @Transactional(timeout = 20)
    @PostMapping
    public ResponseEntity<ClienteDTO> crearCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        try {
            ClienteDTO clienteCreadoDTO = clienteService.crearCliente(clienteDTO);
            LOGGER.info("Cliente creado en base de datos");
            this.eventService.sendClientCreatedEvent(clienteCreadoDTO);
            LOGGER.info("{} : {}", Constantes.TRY_EVENT, clienteDTO.getId());
            return new ResponseEntity<>(clienteCreadoDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constantes.ERROR_INESPERADO);
        }
    }
}
