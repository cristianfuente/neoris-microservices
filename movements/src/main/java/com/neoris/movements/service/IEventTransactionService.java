package com.neoris.movements.service;

import org.springframework.stereotype.Service;

@Service
public interface IEventTransactionService {
    void sendJson(String topic, String json);
}
