package com.neoris.customer.service;

import com.neoris.customer.dto.ClienteDTO;
import org.springframework.stereotype.Service;

@Service
public interface IClienteService {

    ClienteDTO crearCliente(ClienteDTO clienteDTO);

}
