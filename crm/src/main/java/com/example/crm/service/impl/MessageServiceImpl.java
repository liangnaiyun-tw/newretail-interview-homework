package com.example.crm.service.impl;

import com.example.crm.dto.CustomersFilteredByOrderDto;
import com.example.crm.dto.SmsRequestDto;
import com.example.crm.persistence.entity.Message;
import com.example.crm.persistence.mapper.MessageMapper;
import com.example.crm.service.IMessageService;
import com.example.crm.service.ISendSmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageServiceImpl implements IMessageService {

    private final MessageMapper messageMapper;

    @Override
    public int addMessage(Message message) {
        return messageMapper.addMessage(message);
    }

    @Override
    public int updateMessage(Message message) {
        return messageMapper.updateMessage(message);
    }
}
