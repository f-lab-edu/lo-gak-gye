package me.minkh.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Service
public class RateLimitService {

    private final RedisTemplate<String, String> redisTemplate;
    private static final int MAX_REQUEST = 33;
    private static final int TIMEOUT = 1;

    public boolean isLimit(String key) {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        Long count = valueOps.increment(key);
        if (count == null) {
            throw new IllegalArgumentException(key + "는 올바르지 않은 요청입니다.");
        }
        if (count == 1) {
            redisTemplate.expire(key, TIMEOUT, TimeUnit.MINUTES);
        }
        return count > MAX_REQUEST;
    }
}
