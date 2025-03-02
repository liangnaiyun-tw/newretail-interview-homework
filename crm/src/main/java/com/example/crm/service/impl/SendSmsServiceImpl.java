package com.example.crm.service.impl;

import com.example.crm.dto.CustomersFilteredByOrderDto;
import com.example.crm.dto.SmsRequestDto;
import com.example.crm.persistence.entity.Message;
import com.example.crm.service.IMessageService;
import com.example.crm.service.ISendSmsService;
import com.example.crm.utils.enums.MessageStatusEnum;
import com.example.crm.utils.rabbitmq.NotificationProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SendSmsServiceImpl implements ISendSmsService {

    private final IMessageService messageService;
    private final NotificationProducer notificationProducer;
    @Override
    public String sendSms(SmsRequestDto smsRequestDto) {
        StringBuilder errResponse = new StringBuilder();
        for (CustomersFilteredByOrderDto customersFilteredByOrderDto : smsRequestDto.getCustomersFilteredByOrderDtos()) {
            if (customersFilteredByOrderDto.getPhone() != null) {
                String message = messageTemplate(smsRequestDto.getTemplate(), customersFilteredByOrderDto);

                Message recordMessage = new Message.MessageBuilder()
                        .customerId(customersFilteredByOrderDto.getId())
                        .message(message)
                        .createdAt(new Date())
                        .status(0)
                        .build();
                addMessage(recordMessage);
                // send message to rabbitmq
                notificationProducer.sendSmsNotification(recordMessage, customersFilteredByOrderDto);

            } else {
                errResponse.append("Customer " + customersFilteredByOrderDto.getFullName() + " does not have a phone number");
            }
        }


        if (errResponse.length() > 0) {
            throw new IllegalArgumentException(errResponse.toString());
        } else {
            return "All messages sent successfully";
        }
    }

    public String messageTemplate(String template, CustomersFilteredByOrderDto customersFilteredByOrderDto) {
//        template: 親愛的 {fullName}，感謝您的支持！您本月的消費金額為 {amount} 元，別忘了查看我們的最新優惠！

        template = template.replace("{fullName}", customersFilteredByOrderDto.getFullName());
        template = template.replace("{amount}", new BigDecimal(customersFilteredByOrderDto.getOrderAmount()).toString());
        return template;
    }


    @Transactional
    public int addMessage(Message message) {
        return messageService.addMessage(message);
    }



}
