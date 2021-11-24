package com.poc.builder.dto;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;

public class RabbitMessageBuilder {

    public static Message build(byte[] binaryData) {
        return MessageBuilder
                .withBody(binaryData)
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .build();
    }
}
