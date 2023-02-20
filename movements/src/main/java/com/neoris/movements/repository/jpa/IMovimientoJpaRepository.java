package com.neoris.movements.repository.jpa;

import com.neoris.movements.model.Movimiento;
import com.neoris.movements.repository.IMovimientoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovimientoJpaRepository extends JpaRepository<Movimiento, Long>, IMovimientoRepository {

    @Override
    default Movimiento guardarMovimiento(Movimiento movimiento){
        return save(movimiento);
    }


}
