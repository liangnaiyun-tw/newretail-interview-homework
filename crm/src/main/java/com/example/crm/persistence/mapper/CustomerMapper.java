package com.example.crm.persistence.mapper;

import com.example.crm.dto.CustomersFilteredByOrderDto;
import com.example.crm.dto.OrderDto;
import com.example.crm.persistence.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {
    public List<CustomersFilteredByOrderDto> getCustomersFilteredByOrder(OrderDto orderDto);
}
