package com.example.crm.service;

import com.example.crm.persistence.entity.Message;

public interface IMessageService {

    public int addMessage(Message message);
    public int updateMessage(Message message);
}
