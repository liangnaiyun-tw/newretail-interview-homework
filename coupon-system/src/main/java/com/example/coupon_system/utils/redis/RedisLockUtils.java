package com.example.coupon_system.utils.redis;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))

public class RedisLockUtils {
    private final RedisTemplate redisTemplate;

    public boolean lock(String key, String value, long expireTime) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, expireTime, TimeUnit.SECONDS);
    }

    public boolean unlock(String key, String value) {
        Object currentValue = redisTemplate.opsForValue().get(key);
        boolean result = false;
        if(StringUtils.isNotBlank(String.valueOf(currentValue)) && currentValue.equals(value)) {
            result = redisTemplate.opsForValue().getOperations().delete(key);
        }

        return result;
    }
}
