package com.neoris.movements.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.movements.model.Cuenta;
import com.neoris.movements.repository.ICuentaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class KafkaEventCuentaListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaEventCuentaListener.class);
    private final ICuentaRepository cuentaRepository;

    @Autowired
    public KafkaEventCuentaListener(ICuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @KafkaListener(topics = {"cuenta_creada", "cuenta_actualizada"}, groupId = "cuenta")
    public void listenChangeCuentaEvents(String cuentaJson) {
        try {
            guardarCuenta(cuentaJson);
        } catch (Exception e) {
            LOGGER.error("Error procesando eventos de cuenta: {}", e.getMessage());
        }
    }

    @Retryable(value = Exception.class, maxAttempts = -1, backoff = @Backoff(delay = 5000))
    public void guardarCuenta(String cuentaJson) throws JsonProcessingException {
        LOGGER.error("Intentando procesar evento guardar cuenta");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Cuenta cuenta = objectMapper.readValue(cuentaJson, Cuenta.class);
        this.cuentaRepository.guardarCuenta(cuenta);
        LOGGER.info("Cuenta actualizada");
    }

}
