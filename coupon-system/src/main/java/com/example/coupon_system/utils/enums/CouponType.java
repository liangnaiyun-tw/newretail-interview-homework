package com.example.coupon_system.utils.enums;

import java.util.Map;

public enum CouponType {
    Discount(0, "折扣券"),
    Threshold(1, "满减券");

    private final int code;
    private final String name;

    private static Map<Integer, String> map;

    static {
        for (CouponType couponType : CouponType.values()) {
            map.put(couponType.code, couponType.name);
        }
    }

    CouponType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public String geCouponTypeByCode(int code) {
        return map.get(code);
    }
}
