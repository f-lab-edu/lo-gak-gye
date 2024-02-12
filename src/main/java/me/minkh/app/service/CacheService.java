package me.minkh.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class CacheService {

    @Value("${spring.cache.timeout}")
    private int timeout;

    private final ObjectMapper objectMapper;

    private final RedisTemplate<String, String> redisTemplate;

    public <T> T getCache(String cacheKey, TypeReference<T> typeReference) throws JsonProcessingException {
        ValueOperations<String, String> valueOps = getValueOps();
        String cacheValue = valueOps.get(cacheKey);
        if (cacheValue == null) {
            return null;
        }
        return this.objectMapper.readValue(cacheValue, typeReference);
    }

    public <T> T getCache(String cacheKey, Class<T> classType) throws JsonProcessingException {
        ValueOperations<String, String> valueOps = getValueOps();
        String cacheValue = valueOps.get(cacheKey);
        if (cacheValue == null) {
            return null;
        }
        return this.objectMapper.readValue(cacheValue, classType);
    }

    public void setCache(String cacheKey, Object response) throws JsonProcessingException {
        String cacheValue = this.objectMapper.writeValueAsString(response);
        ValueOperations<String, String> valueOps = getValueOps();
        valueOps.set(cacheKey, cacheValue, this.timeout, TimeUnit.SECONDS);
    }

    private ValueOperations<String, String> getValueOps() {
        return redisTemplate.opsForValue();
    }
}
