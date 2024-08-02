package com.non.k4r.manager;

import jakarta.annotation.Resource;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class AccessTokenManager {
    @Resource
    private RedissonClient redissonClient;


    public String generateAndStoreAccessToken(Long userId) {
        String token = UUID.randomUUID().toString().replace("-", "");
        RBucket<Object> bucket = redissonClient.getBucket(STR."access_token:\{userId}:\{token}");

        return token;
    }
}
