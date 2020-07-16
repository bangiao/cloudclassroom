package com.dingxin.cloudclassroom.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MapCache;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.LinkedHashMap;

/**
 * @author jianghuaidi
 * @email jianghuaidi@szdxsoft.com
 * @date 2020/7/16
 */
public class ShiroRedisCacheManager implements CacheManager {

    private RedisTemplate<byte[],byte[]> redisTemplate;

    public ShiroRedisCacheManager(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    /**
     * rediscache有问题  先用mapcache后面再看
     * @param s
     * @param <K>
     * @param <V>
     * @return
     * @throws CacheException
     */
    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return new MapCache<>(s,new LinkedHashMap<>());
    }
}
