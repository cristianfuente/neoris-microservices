package com.neoris.movements.repository;

import com.neoris.movements.model.Cuenta;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICuentaRepository {
    Optional<Cuenta> findCuentaById(Long id);

    Cuenta findCuentaByNumeroCuentaLike(String numeroCuenta);

    Cuenta guardarCuenta(Cuenta cuenta);
}
