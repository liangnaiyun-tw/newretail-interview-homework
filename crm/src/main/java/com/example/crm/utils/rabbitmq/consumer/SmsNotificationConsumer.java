package com.example.crm.utils.rabbitmq.consumer;

import com.example.crm.dto.CustomersFilteredByOrderDto;
import com.example.crm.persistence.entity.Message;
import com.example.crm.service.IMessageService;
import com.example.crm.utils.rabbitmq.NotificationMsg;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.example.crm.utils.rabbitmq.Constants.NAME_SMS_NOTIFICATION_QUEUE;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SmsNotificationConsumer {

    private final IMessageService messageService;

    @RabbitListener(queues = NAME_SMS_NOTIFICATION_QUEUE)
    public void receiveSmsNotification(NotificationMsg<CustomersFilteredByOrderDto> notificationMsg) {
        log.info("Received SMS notification");
        CustomersFilteredByOrderDto customersFilteredByOrderDto = notificationMsg.getData();
        Message message = notificationMsg.getMessage();
        System.out.println("Message sent to " + customersFilteredByOrderDto.getFullName() + " at " + customersFilteredByOrderDto.getPhone() + ": " + message.getMessage());
        message.setSentAt(new Date());
        message.setStatus(1);
        messageService.updateMessage(message);
    }
}
