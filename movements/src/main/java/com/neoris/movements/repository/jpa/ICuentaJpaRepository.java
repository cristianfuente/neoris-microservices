package com.neoris.movements.repository.jpa;

import com.neoris.movements.model.Cuenta;
import com.neoris.movements.repository.ICuentaRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICuentaJpaRepository extends JpaRepository<Cuenta, Long>, ICuentaRepository {

    @Override
    default Optional<Cuenta> findCuentaById(Long id) {
        return findById(id);
    }

    @Override
    Cuenta findCuentaByNumeroCuentaLike(String numeroCuenta);

    @Override
    default Cuenta guardarCuenta(Cuenta cuenta) {
        return save(cuenta);
    }

}
