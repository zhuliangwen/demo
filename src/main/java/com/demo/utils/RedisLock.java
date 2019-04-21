package com.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RedisLock {
 
    public static final int LOCK_EXPIRE = 3000; // ms
 
    @Autowired
    private StringRedisTemplate redisTemplate;
 
 
    /**
     *  分布式锁
     *
     * @param key key值
     * @return 是否获取到
     */
    public boolean lock(String key) {
        String lock = key;
        try {
            return (Boolean) redisTemplate.execute((RedisCallback) connection -> {
                long expireAt = System.currentTimeMillis() + LOCK_EXPIRE;
                Boolean acquire = connection.setNX(lock.getBytes(), String.valueOf(expireAt).getBytes());
                if (acquire) {
                    return true;
                } else {
                    //判断该key上的值是否过期了
                    byte[] value = connection.get(lock.getBytes());
                    if (Objects.nonNull(value) && value.length > 0) {
                        long expireTime = Long.parseLong(new String(value));
                        if (expireTime < System.currentTimeMillis()) {
                            // 如果锁已经过期
                            byte[] oldValue = connection.getSet(lock.getBytes(), String.valueOf(System.currentTimeMillis() + LOCK_EXPIRE).getBytes());
                            // 防止死锁
                            return Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
                        }
                    }
                }
                return false;
            });
        } finally {
            RedisConnectionUtils.unbindConnection(redisTemplate.getConnectionFactory());
        }
    }
 
 
//    @Autowired
//    private RedisService redisService;
 
    /**
     * 删除锁
     *
     * @param key
     */
    public void delete(String key) {
        try {
            redisTemplate.delete(key);
        } finally {
            RedisConnectionUtils.unbindConnection(redisTemplate.getConnectionFactory());
        }
    }
 
}
