package com.neoris.customer.mapper;

import com.neoris.customer.dto.ClienteDTO;
import com.neoris.customer.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IClienteMapper {

    IClienteMapper INSTANCE = Mappers.getMapper(IClienteMapper.class);

    ClienteDTO toClienteDTO(Cliente cliente);

    Cliente toCliente(ClienteDTO clienteDTO);

}
