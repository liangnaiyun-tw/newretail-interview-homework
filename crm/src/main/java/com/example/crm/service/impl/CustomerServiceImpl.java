package com.example.crm.service.impl;

import com.example.crm.dto.CustomersFilteredByOrderDto;
import com.example.crm.dto.OrderDto;
import com.example.crm.persistence.mapper.CustomerMapper;
import com.example.crm.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public List<CustomersFilteredByOrderDto> getCustomersFilteredByOrder(OrderDto orderDto) {
        return customerMapper.getCustomersFilteredByOrder(orderDto);
    }
}
