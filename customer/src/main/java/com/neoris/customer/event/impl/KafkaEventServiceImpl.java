package com.neoris.customer.event.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.customer.dto.ClienteDTO;
import com.neoris.customer.event.IEventService;
import com.neoris.customer.util.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

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
            LOGGER.info("{} : {}", Constantes.TRY_EVENT, clienteDTO.getId());
            ObjectMapper objectMapper = new ObjectMapper();
            ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(Constantes.TOPIC_CREAR_CLIENTE, objectMapper.writeValueAsString(clienteDTO));
            future.addCallback(new ListenableFutureCallback<>() {
                @Override
                public void onFailure(Throwable ex) {
                    LOGGER.error("{} : {}", Constantes.ERROR_EVENT, ex.getMessage());
                    sendClientCreatedEvent(clienteDTO);
                }

                @Override
                public void onSuccess(SendResult<String, String> result) {
                    LOGGER.info("{} : {}", Constantes.SUCCESS_EVENT, result.getRecordMetadata());
                }
            });
        } catch (Exception e) {
            LOGGER.error("{} : {}", Constantes.ERROR_EVENT, e.getMessage());
        }

    }

}
