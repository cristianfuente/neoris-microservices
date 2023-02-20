package com.neoris.movements.repository.jpa;

import com.neoris.movements.model.TipoTransaccion;
import com.neoris.movements.repository.ITipoTransaccionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITipoMovimientoJpaRepository extends JpaRepository<TipoTransaccion, Long>, ITipoTransaccionRepository {

    @Override
    default Optional<TipoTransaccion> findTipoMovimientoById(Long id) {
        return findById(id);
    }

}
