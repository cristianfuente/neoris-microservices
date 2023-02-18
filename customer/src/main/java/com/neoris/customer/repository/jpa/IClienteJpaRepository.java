package com.neoris.customer.repository.jpa;

import com.neoris.customer.model.Cliente;
import com.neoris.customer.repository.IClienteRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClienteJpaRepository extends JpaRepository<Cliente, Long>, IClienteRepository {

    @Override
    default Cliente guardarCliente(Cliente cliente) {
        return save(cliente);
    }

    @Override
    default Optional<Cliente> findClienteById(Long id) {
        return findById(id);
    }
}
