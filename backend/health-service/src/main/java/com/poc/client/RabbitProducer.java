package com.poc.client;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class RabbitProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(String exchange, String routingKey, Message message) {
        log.info("Enviando mensagem {} para exchange {} e chave {}", message, exchange, routingKey);
        rabbitTemplate.send(exchange, routingKey, message);
    }
}
