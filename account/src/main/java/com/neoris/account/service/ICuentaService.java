package com.neoris.account.service;

import com.neoris.account.dto.CuentaDTO;
import com.neoris.account.model.Cuenta;
import org.springframework.stereotype.Service;

@Service
public interface ICuentaService {
    Cuenta crearCuenta(CuentaDTO cuentaDTO);
}
