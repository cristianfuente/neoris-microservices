package com.neoris.movements.service.impl;

import com.neoris.movements.service.IEventTransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaEventTransactionServiceImpl implements IEventTransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaEventTransactionServiceImpl.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaEventTransactionServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendJson(String topic, String json){
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, json);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                LOGGER.error("{} : {}", "Error", ex.getMessage());
                sendJson(topic, json);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                LOGGER.info("{} : {}", "Evento creado", result.getRecordMetadata());
            }
        });
    }


}
