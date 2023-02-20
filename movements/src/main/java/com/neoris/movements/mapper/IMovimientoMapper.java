package com.neoris.movements.mapper;

import com.neoris.movements.dto.MovimientoDTO;
import com.neoris.movements.model.Movimiento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IMovimientoMapper {

    IMovimientoMapper INSTANCE = Mappers.getMapper(IMovimientoMapper.class);

    @Mapping(target = "idCuenta", ignore = true)
    Movimiento toMovimiento(MovimientoDTO movimientoDTO);

    @Mapping(target = "nombreCliente", source = "cuenta.nombreCliente")
    @Mapping(target = "numeroCuenta", source = "cuenta.numeroCuenta")
    @Mapping(target = "estadoCuenta", source = "cuenta.estado")
    @Mapping(target = "tipoCuenta", source = "cuenta.tipoCuenta")
    @Mapping(target = "tipoMovimiento", source = "tipoTransaccion.nombre")
    MovimientoDTO toMovimientoDTO(Movimiento movimiento);

}
