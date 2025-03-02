package com.example.crm.persistence.mapper;

import com.example.crm.dto.CustomersFilteredByOrderDto;
import com.example.crm.dto.OrderDto;
import com.example.crm.persistence.entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
    public int addMessage(Message message);
    public int updateMessage(Message message);
}
