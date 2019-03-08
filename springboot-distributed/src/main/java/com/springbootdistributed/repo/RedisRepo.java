package com.springbootdistributed.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class RedisRepo {

    @Autowired
    private RedisTemplate redisTemplate;

    public Boolean set(String key, Object value) {
        return (Boolean) redisTemplate.execute((RedisCallback<Boolean>) conn -> {
            StringRedisSerializer serializer = new StringRedisSerializer();
            Boolean set = conn.set(serializer.serialize(key), serializer.serialize(value.toString()));
            conn.close();
            return set;
        });
    }

    public Object get(String key) {
        return redisTemplate.execute((RedisCallback<String>) conn -> {
            StringRedisSerializer serializer = new StringRedisSerializer();
            String value = serializer.deserialize(conn.get(serializer.serialize(key)));
            conn.close();
            return value;
        });
    }

    public void decrement(String key) {
        redisTemplate.execute((RedisCallback<String>) conn -> {
            StringRedisSerializer serializer = new StringRedisSerializer();
            conn.decr(serializer.serialize(key));
            conn.close();
            return "";
        });
    }


}
