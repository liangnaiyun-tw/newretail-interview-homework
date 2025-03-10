package com.example.coupon_system.utils.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserCouponStatus {
    UNUSED(0, "未使用"),
    USED(1, "已使用"),
    EXPIRED(2, "已過期");

    private final int code;
    private final String name;



    UserCouponStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    private static Map<Integer, String> map = new HashMap<>();

    static {
        for (UserCouponStatus userCouponStatus : UserCouponStatus.values()) {
            map.put(userCouponStatus.code, userCouponStatus.name);
        }
    }

    public static String getUserCouponStatusByCode(int code) {
        return map.get(code);
    }

    public int getCode() {
        return this.code;
    }
}
