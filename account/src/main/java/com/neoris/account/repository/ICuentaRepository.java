package com.neoris.account.repository;

import com.neoris.account.model.Cuenta;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICuentaRepository {

    Cuenta crearCuenta(Cuenta cuenta);

    Optional<Cuenta> findCuentById(Long id);
}
