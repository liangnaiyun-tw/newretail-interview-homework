package com.example.crm.persistence.entity;

import lombok.Data;

@Data
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String phone;
    private String email;
    private String createdAt;
    private String updatedAt;
}
