package com.neoris.customer.event.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.customer.dto.ClienteDTO;
import com.neoris.customer.event.IEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class KafkaEventServiceImpl implements IEventService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaEventServiceImpl.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaEventServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendClientCreatedEvent(ClienteDTO clienteDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            kafkaTemplate.send("cliente-creado", objectMapper.writeValueAsString(clienteDTO));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error inesperado");
        }
    }

}
