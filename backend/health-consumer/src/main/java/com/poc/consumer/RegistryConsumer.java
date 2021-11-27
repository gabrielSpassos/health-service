package com.poc.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.event.RegistryEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
@ConditionalOnProperty(value="rabbitmq.registry.enabled", havingValue = "true")
public class RegistryConsumer {

    private final ObjectMapper objectMapper;

    @RabbitListener(containerFactory = "containerFactory",
            bindings = @QueueBinding(value = @Queue(value = "${rabbitmq.registry.queue.name}", durable = "true"),
            exchange = @Exchange(value = "${rabbitmq.exchange.name}", type = "${rabbitmq.exchange.type}"),
            key = "${rabbitmq.exchange.routing-key}"))
    public void receive(Message message) {
        log.info("Registro consumido {}", message);
        try {
            RegistryEvent event = objectMapper.readValue(message.getBody(), RegistryEvent.class);
            log.info("consumido evento {}", event);
        } catch (Exception e) {
            log.error("Erro ao processar a mensagem registro {}", message, e);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
