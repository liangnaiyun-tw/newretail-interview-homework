package com.example.coupon_system.controller;

import com.example.coupon_system.dto.UserCouponDto;
import com.example.coupon_system.persistence.entity.UserCoupon;
import com.example.coupon_system.service.ICouponService;
import com.example.coupon_system.utils.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/reset")
    public String resetCoupon() {
        try {
            couponService.resetCoupon();
            return "Coupon reset";
        } catch (Exception e) {
            return "Failed to reset coupon: " + e.getMessage();
        }
    }


    @PostMapping("/getCoupon")
    public ResponseEntity<ApiResponse> getCoupon(@RequestBody UserCouponDto userCouponDto) {
        try {
            String result = couponService.getCoupon(userCouponDto);
            if (result.isBlank()){
                return ResponseEntity.ok(new ApiResponse.ApiResponseBuilder()
                        .message("Coupon retrieved successfully")
                        .status(HttpStatus.OK)
                        .build());
            } else {
                return ResponseEntity.ok(new ApiResponse.ApiResponseBuilder()
                        .message(result)
                        .status(HttpStatus.OK)
                        .build());
            }

        } catch (Exception e) {
            ApiResponse response = new ApiResponse.ApiResponseBuilder()
                    .message("Failed to get coupon by user id: " + e.getMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }



}
