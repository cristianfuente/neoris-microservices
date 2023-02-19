package com.neoris.account.repository.jpa;

import com.neoris.account.model.Cuenta;
import com.neoris.account.repository.ICuentaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICuentaJpaRepository extends JpaRepository<Cuenta, Long>, ICuentaRepository {

    @Override
    default Cuenta crearCuenta(Cuenta cuenta) {
        return save(cuenta);
    }

    @Override
    default Optional<Cuenta> findCuentById(Long id) {
        return findById(id);
    }
}
