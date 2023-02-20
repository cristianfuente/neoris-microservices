package com.neoris.movements.mapper;

import com.neoris.movements.dto.MovimientoDTO;
import com.neoris.movements.model.Movimiento;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IMovimientoMapper {

    IMovimientoMapper INSTANCE = Mappers.getMapper(IMovimientoMapper.class);

    Movimiento toMovimiento(MovimientoDTO movimientoDTO);

    MovimientoDTO toMovimientoDTO(Movimiento movimiento);

}
