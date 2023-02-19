package com.neoris.account.repository;

import com.neoris.account.model.Cliente;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository {

    Cliente guardarCliente(Cliente cliente);

}
