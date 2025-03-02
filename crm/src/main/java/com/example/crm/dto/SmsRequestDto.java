package com.example.crm.dto;

import com.example.crm.persistence.entity.Customer;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SmsRequestDto implements Serializable {
    List<CustomersFilteredByOrderDto> customersFilteredByOrderDtos;
    String template;

    public SmsRequestDto(List<CustomersFilteredByOrderDto> customersFilteredByOrderDtos, String template) {
        this.customersFilteredByOrderDtos = customersFilteredByOrderDtos;
        this.template = template;
    }
}
