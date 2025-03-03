package com.example.coupon_system.controller;

import com.example.coupon_system.service.ICouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coupon")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CouponController {

    private final ICouponService couponService;
    @GetMapping("/initialize")
    public String initializeCoupon() {
        try {
            couponService.initializeCoupon();
            return "Coupon initialized";
        } catch (Exception e) {
            return "Failed to initialize coupon: " + e.getMessage();
        }
    }
}
