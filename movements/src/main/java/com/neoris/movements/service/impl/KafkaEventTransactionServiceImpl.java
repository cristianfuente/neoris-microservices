package com.neoris.movements.service.impl;

import com.neoris.movements.service.IEventTransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaEventTransactionServiceImpl implements IEventTransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaEventTransactionServiceImpl.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaEventTransactionServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


}
