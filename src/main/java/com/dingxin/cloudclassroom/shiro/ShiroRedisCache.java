package com.dingxin.cloudclassroom.shiro;

import com.dingxin.cloudclassroom.utils.ObjectUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;

/**
 * @author jianghuaidi
 * @email jianghuaidi@szdxsoft.com
 * @date 2020/7/16
 */
public class ShiroRedisCache<K,V> implements Cache<K,V> {
    private RedisTemplate redisTemplate;
    private String prefix = "shiro_redis_cache";

    public String getPrefix() {
        return prefix+":";
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public ShiroRedisCache(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    public ShiroRedisCache(RedisTemplate redisTemplate, String prefix){
        this(redisTemplate);
        this.prefix = prefix;
    }

    @Override
    public V get(K k) throws CacheException {
        if (k == null) {
            return null;
        }
        String key = k.toString();
        byte[] bytes = getBytesKey(k);
        return (V)redisTemplate.opsForValue().get(key);

    }

    @Override
    public V put(K k, V v) throws CacheException {
        if (k== null || v == null) {
            return null;
        }
        String key = k.toString();
        byte[] bytes = getBytesKey(k);
        redisTemplate.opsForValue().set(key, v);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        if(k==null){
            return null;
        }
        String key = k.toString();
        byte[] bytes =getBytesKey(k);
        V v = (V)redisTemplate.opsForValue().get(key);
        redisTemplate.delete(key);
        return v;
    }

    @Override
    public void clear() throws CacheException {
        redisTemplate.getConnectionFactory().getConnection().flushDb();

    }

    @Override
    public int size() {
        return redisTemplate.getConnectionFactory().getConnection().dbSize().intValue();
    }

    @Override
    public Set<K> keys() {
        byte[] bytes = (getPrefix()+"*").getBytes();
        Set<byte[]> keys = redisTemplate.keys(bytes);
        Set<K> sets = new HashSet<>();
        for (byte[] key:keys) {
            sets.add((K)key);
        }
        return sets;
    }

    @Override
    public Collection<V> values() {
        Set<K> keys = keys();
        List<V> values = new ArrayList<>(keys.size());
        for(K k :keys){
            values.add(get(k));
        }
        return values;
    }

    private byte[] getBytesKey(K key){
        if(key instanceof String){
            String prekey = this.getPrefix() + key;
            return prekey.getBytes();
        }else {
            return ObjectUtils.serialize(key);
        }
    }

}
