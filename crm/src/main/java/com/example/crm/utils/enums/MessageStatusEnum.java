package com.example.crm.utils.enums;

import java.util.HashMap;
import java.util.Map;

public enum MessageStatusEnum {

    PENDING(0, "PENDING"),
    SENT(1, "SENT"),
    FAILED(2, "FAILED");

    private String value;
    private int key;

    private static Map<Integer, String> map = new HashMap<>();

    static {
        for (MessageStatusEnum messageStatusEnum : MessageStatusEnum.values()) {
            map.put(messageStatusEnum.key, messageStatusEnum.value);
        }
    }

    MessageStatusEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getValueByKey(int key) {
        return map.get(key);
    }
}
