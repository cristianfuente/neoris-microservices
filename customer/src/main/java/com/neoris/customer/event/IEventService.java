package com.neoris.customer.event;

import com.neoris.customer.dto.ClienteDTO;
import org.springframework.stereotype.Service;

@Service
public interface IEventService {
    void sendClientCreatedEvent(ClienteDTO clienteDTO);
}
