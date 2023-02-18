package com.neoris.customer.service.impl;

import com.neoris.customer.dto.ClienteDTO;
import com.neoris.customer.mapper.IClienteMapper;
import com.neoris.customer.model.Cliente;
import com.neoris.customer.repository.IClienteRepository;
import com.neoris.customer.service.IClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteServiceImpl implements IClienteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteServiceImpl.class);
    private final IClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(@Qualifier("IClienteJpaRepository") IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional(timeout = 30)
    public Cliente crearCliente(ClienteDTO clienteDTO) {
        try {
            Cliente cliente = IClienteMapper.INSTANCE.toCliente(clienteDTO);
            return this.clienteRepository.guardarCliente(cliente);
        } catch (Exception e) {
            LOGGER.error("Error en la creacion del cliente {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error inesperado");
        }
    }
}
