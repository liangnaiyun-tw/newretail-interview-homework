package com.example.crm.controller;

import com.example.crm.dto.CustomersFilteredByOrderDto;
import com.example.crm.dto.OrderDto;
import com.example.crm.persistence.entity.Customer;
import com.example.crm.service.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping("/filteredByOrder")
    public CollectionModel<EntityModel<CustomersFilteredByOrderDto>> getCustomersFilteredByOrder(@RequestBody OrderDto orderDto) {

        List<EntityModel<CustomersFilteredByOrderDto>> customers = customerService.getCustomersFilteredByOrder(orderDto).stream()
                .map(customer  -> EntityModel.of(customer))
                .collect(Collectors.toList());

        return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class).getCustomersFilteredByOrder(orderDto)).withSelfRel());
    }
}
