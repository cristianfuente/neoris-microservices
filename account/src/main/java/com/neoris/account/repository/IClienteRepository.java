package com.neoris.account.repository;

import com.neoris.account.model.Cliente;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClienteRepository {

    Cliente guardarCliente(Cliente cliente);

    Optional<Cliente> findClienteById(Long id);

}
