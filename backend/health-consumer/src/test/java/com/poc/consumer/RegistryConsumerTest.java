package com.poc.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.event.RegistryEvent;
import com.poc.stub.RegistryStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;

@ExtendWith(MockitoExtension.class)
class RegistryConsumerTest {

    @InjectMocks
    private RegistryConsumer registryConsumer;

    @Spy
    private ObjectMapper objectMapper;

    @Test
    public void shouldConsumeRegistry() throws JsonProcessingException {
        RegistryEvent event = RegistryStub.createEvent();

        byte[] binaryData = objectMapper.writeValueAsBytes(event);

        Message message = MessageBuilder.withBody(binaryData).setContentType(MessageProperties.CONTENT_TYPE_JSON).build();

        registryConsumer.receive(message);
    }
}