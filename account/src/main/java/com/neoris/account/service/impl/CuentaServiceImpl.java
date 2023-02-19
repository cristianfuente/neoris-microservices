package com.neoris.account.service.impl;

import com.neoris.account.dto.CuentaDTO;
import com.neoris.account.mapper.ICuentaMapper;
import com.neoris.account.model.Cuenta;
import com.neoris.account.repository.IClienteRepository;
import com.neoris.account.repository.ICuentaRepository;
import com.neoris.account.service.ICuentaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
public class CuentaServiceImpl implements ICuentaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CuentaServiceImpl.class);
    private final ICuentaRepository cuentaRepository;
    private final IClienteRepository clienteRepository;

    @Autowired
    public CuentaServiceImpl(@Qualifier("ICuentaJpaRepository") ICuentaRepository cuentaRepository,
                             @Qualifier("IClienteJpaRepository") IClienteRepository clienteRepository) {
        this.cuentaRepository = cuentaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional
    public Cuenta crearCuenta(CuentaDTO cuentaDTO) {
        try {
            Cuenta cuenta = ICuentaMapper.INSTANCE.toCuenta(cuentaDTO);
            return this.cuentaRepository.crearCuenta(cuenta);
        } catch (Exception e) {
            LOGGER.error("Error creacion de cuenta: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creacion de cuenta");
        }
    }
}
