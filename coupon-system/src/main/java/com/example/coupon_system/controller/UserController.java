package com.example.coupon_system.controller;

import com.example.coupon_system.persistence.entity.User;
import com.example.coupon_system.service.IUserService;
import com.example.coupon_system.utils.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    @PostMapping("create")
    public ResponseEntity<ApiResponse> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            EntityModel<User> entityModel = EntityModel.of(createdUser).add(
                    linkTo(methodOn(UserController.class).createUser(user)).withSelfRel()
            );
            ApiResponse response = new ApiResponse.ApiResponseBuilder()
                    .data(entityModel)
                    .message("User created successfully")
                    .status(HttpStatus.CREATED)
                    .build();
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse.ApiResponseBuilder()
                    .message("Failed to create user: " + e.getMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
