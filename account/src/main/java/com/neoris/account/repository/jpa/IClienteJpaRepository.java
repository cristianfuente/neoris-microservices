package com.neoris.account.repository.jpa;

import com.neoris.account.model.Cliente;
import com.neoris.account.repository.IClienteRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteJpaRepository extends JpaRepository<Cliente, Long>, IClienteRepository {

    @Override
    default Cliente guardarCliente(Cliente cliente) {
        return save(cliente);
    }
}
