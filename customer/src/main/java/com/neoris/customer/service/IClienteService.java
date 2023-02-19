package com.neoris.customer.service;

import com.neoris.customer.dto.ClienteDTO;
import com.neoris.customer.model.Cliente;
import org.springframework.stereotype.Service;

@Service
public interface IClienteService {

    Cliente crearCliente(ClienteDTO clienteDTO);

}
