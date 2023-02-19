package com.neoris.account.mapper;

import com.neoris.account.dto.CuentaDTO;
import com.neoris.account.model.Cuenta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ICuentaMapper {

    ICuentaMapper INSTANCE = Mappers.getMapper(ICuentaMapper.class);

    @Mapping(target = "nombreCliente", source = "cliente.nombre")
    CuentaDTO toCuentaDTO(Cuenta cuenta);

    Cuenta toCuenta(CuentaDTO cuentaDTO);

}
