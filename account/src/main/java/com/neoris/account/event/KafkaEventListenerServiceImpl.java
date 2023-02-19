package com.neoris.account.event;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.account.model.Cliente;
import com.neoris.account.repository.IClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class KafkaEventListenerServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaEventListenerServiceImpl.class);

    private final IClienteRepository clienteRepository;

    @Autowired
    public KafkaEventListenerServiceImpl(@Qualifier("IClienteJpaRepository") IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @KafkaListener(topics = {"cliente-creado", "cliente-actualizado"}, groupId = "cliente-id")
    public void saveCliente(String clienteJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Cliente cliente = objectMapper.readValue(clienteJson, Cliente.class);
            Cliente clienteGuardado = clienteRepository.guardarCliente(cliente);
            LOGGER.info("cliente guardado {} id {} nombre {}", clienteGuardado.getNombre(), clienteGuardado.getId(), clienteGuardado.getNombre());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error inesperado");
        }
    }

}
