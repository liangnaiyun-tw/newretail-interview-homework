package com.example.coupon_system.controller;

import com.example.coupon_system.dto.UserCouponDto;
import com.example.coupon_system.persistence.entity.UserCoupon;
import com.example.coupon_system.service.IUserCouponService;
import com.example.coupon_system.utils.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import java.util.List;

@RestController
@RequestMapping("/userCoupon")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserCouponController {

    private final IUserCouponService userCouponService;

    @PostMapping("/redeem")
    public ResponseEntity<ApiResponse<EntityModel<UserCoupon>>> redeemCoupon(@RequestBody UserCouponDto userCouponDto) {
        try {
            UserCoupon userCoupon = userCouponService.redeemCoupon(userCouponDto);
            EntityModel<UserCoupon> userCouponEntityModel = EntityModel.of(userCoupon)
                    .add(linkTo(methodOn(UserCouponController.class).redeemCoupon(userCouponDto)).withSelfRel());
            ApiResponse response = new ApiResponse.ApiResponseBuilder<>()
                    .message("Coupon redeemed successfully")
                    .status(HttpStatus.OK)
                    .data(userCouponEntityModel)
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse.ApiResponseBuilder<>()
                    .message("Failed to redeem coupon: " + e.getMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
