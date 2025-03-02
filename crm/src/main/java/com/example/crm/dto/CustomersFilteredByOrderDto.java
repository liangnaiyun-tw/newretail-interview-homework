package com.example.crm.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class CustomersFilteredByOrderDto implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String phone;
    private String email;
    private Date createdAt;
    private Date updatedAt;
    private int orderId;
    private Date orderDate;
    private float orderAmount;

}
