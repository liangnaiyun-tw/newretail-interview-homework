package com.example.coupon_system.utils.enums;

import java.util.Map;

public enum LogOperationType {
    CREATE(0, "新增"),
    DELETE(1, "删除"),
    UPDATE(2, "更新"),
    SELECT(3, "查询");

    private final int code;
    private final String name;

    private static Map<Integer, String> map;

    static {
        for (LogOperationType logOperationType : LogOperationType.values()) {
            map.put(logOperationType.code, logOperationType.name);
        }
    }

    LogOperationType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getLogOperationTypeByCode(int code) {
        return map.get(code);
    }
}
