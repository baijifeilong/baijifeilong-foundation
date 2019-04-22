package io.github.baijifeilong.foundation.cache;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * Created by BaiJiFeiLong@gmail.com at 2019-04-19 10:46
 */
@SuppressWarnings({"WeakerAccess", "unused", "unchecked", "ConstantConditions"})
@Slf4j
@Component
public class CacheHelper implements InitializingBean {

    @Resource
    @Getter
    private RedisTemplate<String, Object> redisTemplate;

    public <T> T takeOrPut(String key, Supplier<T> supplier) {
        T t;
        if (hasKey(key)) {
            t = take(key);
            log.info("缓存已命中: {} => {}", key, t);
        } else {
            t = put(key, supplier.get());
            log.info("缓存未命中,重新计算: {} => {}", key, t);
        }
        return t;
    }

    public <T> T takeOrPutWithTtl(String key, Supplier<T> supplier, int time, TimeUnit timeUnit) {
        T t;
        if (hasKey(key)) {
            t = take(key);
            log.info("缓存已命中: {} => {}", key, t);
        } else {
            t = putWithTtl(key, supplier.get(), time, timeUnit);
            log.info("缓存未命中,重新计算: {} => {}", key, t);
        }
        return t;
    }

    public <T> T take(String key) {
        Object value = redisTemplate.opsForValue().get(key);
        log.info("[读缓存] {} => {}", key, value);
        return (T) value;
    }


    public <T> T put(String key, T value) {
        log.info("[写缓存] {} => {}", key, value);
        redisTemplate.opsForValue().set(key, value);
        return value;
    }

    public <T> T putWithTtl(String key, T value, int time, TimeUnit timeUnit) {
        log.info("[写缓存] {} => {} ({} {})", key, value, time, timeUnit);
        redisTemplate.opsForValue().set(key, value, time, timeUnit);
        return value;
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    @Override
    public void afterPropertiesSet() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    }
}
