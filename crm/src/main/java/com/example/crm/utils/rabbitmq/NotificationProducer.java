package com.example.crm.utils.rabbitmq;

import com.example.crm.dto.CustomersFilteredByOrderDto;
import com.example.crm.persistence.entity.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.crm.utils.rabbitmq.Constants.NAME_NOTIFICATION_TOPIC_EXCHANGE;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NotificationProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendSmsNotification(Message message, CustomersFilteredByOrderDto customersFilteredByOrderDto) {
        NotificationMsg notificationMsg = new NotificationMsg.NotificationMsgBuilder()
                .message(message)
                .routingKey("message.notification.sms")
                .data(customersFilteredByOrderDto)
                .build();

        rabbitTemplate.convertAndSend(NAME_NOTIFICATION_TOPIC_EXCHANGE, notificationMsg.getRoutingKey(), notificationMsg);

    }
}
