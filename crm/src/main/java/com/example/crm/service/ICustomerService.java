package com.example.crm.service;

import com.example.crm.dto.CustomersFilteredByOrderDto;
import com.example.crm.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ICustomerService {

    List<CustomersFilteredByOrderDto> getCustomersFilteredByOrder(OrderDto orderDto);
}
