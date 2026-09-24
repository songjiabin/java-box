package com.demo.spring.security.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

// Redis 存取工具，统一处理对象的 JSON 序列化和过期时间。
@Component
public class RedisCache {

    // 字符串 Redis 模板，负责执行 Redis 的基础读写命令。
    private final StringRedisTemplate redisTemplate;

    // Jackson 对象转换器，负责 Java 对象和 JSON 字符串之间的转换。
    private final ObjectMapper objectMapper;

    /**
     * 创建 Redis 缓存工具。
     *
     * @param redisTemplate Spring Boot 自动配置的字符串 Redis 模板
     * @param objectMapper Spring Boot 自动配置的 JSON 转换器
     */
    public RedisCache(StringRedisTemplate redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    /**
     * 保存对象到 Redis，使用 Redis 默认的永久有效时间。
     *
     * @param key Redis 键
     * @param value 要保存的对象
     * @param <T> 对象类型
     */
    public <T> void setCacheObject(String key, T value) {
        // 将对象转为 JSON，避免依赖 Java 原生序列化。
        String jsonValue = serialize(value);
        redisTemplate.opsForValue().set(key, jsonValue);
    }

    /**
     * 保存对象到 Redis，并设置自动过期时间。
     *
     * @param key Redis 键
     * @param value 要保存的对象
     * @param timeout 过期时长
     * @param unit 过期时长的单位
     * @param <T> 对象类型
     */
    public <T> void setCacheObject(String key, T value, long timeout, TimeUnit unit) {
        // 将对象转为 JSON，避免依赖 Java 原生序列化。
        String jsonValue = serialize(value);
        redisTemplate.opsForValue().set(key, jsonValue, timeout, unit);
    }

    /**
     * 从 Redis 读取对象。
     *
     * @param key Redis 键
     * @param clazz 目标对象类型
     * @param <T> 对象类型
     * @return Redis 中的对象，不存在时返回 null
     */
    public <T> T getCacheObject(String key, Class<T> clazz) {
        // 读取 Redis 中保存的 JSON 字符串。
        String jsonValue = redisTemplate.opsForValue().get(key);
        if (jsonValue == null) {
            return null;
        }

        try {
            // 将 JSON 字符串恢复为调用方指定的 Java 类型。
            return objectMapper.readValue(jsonValue, clazz);
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException("Redis 缓存数据解析失败，key=" + key, exception);
        }
    }

    /**
     * 删除指定缓存。
     *
     * @param key Redis 键
     * @return 删除成功返回 true，否则返回 false
     */
    public boolean deleteObject(String key) {
        // Redis 删除操作返回 Boolean，这里统一转换为基本 boolean。
        Boolean deleted = redisTemplate.delete(key);
        return Boolean.TRUE.equals(deleted);
    }

    /**
     * 判断 Redis 中是否存在指定键。
     *
     * @param key Redis 键
     * @return 存在返回 true，否则返回 false
     */
    public boolean hasKey(String key) {
        // 由 Redis 直接判断键是否存在，避免先读取完整缓存内容。
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    /**
     * 设置指定缓存的过期时间。
     *
     * @param key Redis 键
     * @param timeout 过期时长
     * @param unit 过期时长的单位
     * @return 设置成功返回 true，否则返回 false
     */
    public boolean expire(String key, long timeout, TimeUnit unit) {
        // 为登录信息设置过期时间，避免缓存永久占用 Redis 内存。
        return Boolean.TRUE.equals(redisTemplate.expire(key, timeout, unit));
    }

    /**
     * 获取指定缓存的剩余有效时间。
     *
     * @param key Redis 键
     * @param unit 返回时间使用的单位
     * @return 剩余时间；键不存在或没有设置过期时间时由 Redis 返回对应状态值
     */
    public long getExpire(String key, TimeUnit unit) {
        // 查询 Redis 返回的剩余有效时间。
        Long remainingTime = redisTemplate.getExpire(key, unit);
        return Objects.requireNonNullElse(remainingTime, -2L);
    }

    /**
     * 将 Java 对象序列化为 JSON 字符串。
     *
     * @param value 要序列化的对象
     * @return JSON 字符串
     */
    private String serialize(Object value) {
        Objects.requireNonNull(value, "Redis 缓存对象不能为空");
        try {
            // 使用 JSON 保存对象，便于跨服务读取并避免 JDK 序列化兼容问题。
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException("Redis 缓存数据序列化失败", exception);
        }
    }
}
