package com.neoris.account.service;

import org.springframework.stereotype.Service;

@Service
public interface IEventCuentaService {
    void sendJson(String topic, String json);
}
