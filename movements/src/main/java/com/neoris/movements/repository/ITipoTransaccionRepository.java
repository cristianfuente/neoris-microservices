package com.neoris.movements.repository;

import com.neoris.movements.model.TipoTransaccion;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITipoTransaccionRepository {

    Optional<TipoTransaccion> findTipoMovimientoById(Long id);
}
