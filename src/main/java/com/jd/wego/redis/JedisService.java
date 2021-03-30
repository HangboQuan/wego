package com.jd.wego.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author hbquan
 * @date 2021/3/30 17:23
 */
@Service
public class JedisService {

    @Autowired
    JedisPool jedisPool;


    public <T> T getKey(KeyPrefix prefix, String key, Class<T> clazz){
        Jedis jedis = null;

        try{
            jedis = jedisPool.getResource();
            // 拼接为完整的一个Redis key
            String real = prefix.getPrefix() + key;
            String str = jedis.get(real);
            return stringToBean(str, clazz);

        }finally {
            // 将jedis连接放入连接池中
            returnToPool(jedis);
        }

    }

    public <T> Boolean setKey(KeyPrefix prefix, String key, T value){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if(str == null || str.length() <= 0){
                return false;
            }
            String real = prefix.getPrefix() + key;
            if(prefix.expireSeconds() <= 0){
                jedis.set(real, str);
            }else{
                jedis.setex(real, prefix.expireSeconds(), str);
            }
            return true;
        }finally{
            returnToPool(jedis);
        }
    }

    public static <T> T stringToBean(String str, Class<T> clazz){
        if(str == null || str.length() < 0 || clazz == null){
            return null;
        }
        if(int.class == clazz || Integer.class == clazz){
            return (T)Integer.valueOf(str);
        }else if(String.class == clazz){
            return (T)str;
        }else if(long.class == clazz || Long.class == clazz){
            return (T)Long.valueOf(str);
        }else{
            // 转为Java对象
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    public static <T> String beanToString(T value){
        if(value == null){
            return null;
        }
        Class<?> clazz = value.getClass();
        if((int.class == clazz) || (Integer.class == clazz)){
            return "" + value;
        }else if(String.class == clazz){
            return (String)value;
        }else if(long.class == clazz || Long.class == clazz){
            return String.valueOf(value);
        }else{
            // 将对象转为字符串
            return JSON.toJSONString(value);
        }
    }

    private void returnToPool(Jedis jedis){
        if(jedis != null){
            jedis.close();
        }
    }

    public <T>Long incr(KeyPrefix prefix, String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String real = prefix.getPrefix() + key;
            return jedis.incr(real);
        }finally {
            returnToPool(jedis);
        }
    }

    public <T>Long decr(KeyPrefix prefix, String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String real = prefix.getPrefix() + key;
            return jedis.decr(real);
        }finally {
            returnToPool(jedis);
        }
    }

    public <T>Boolean exists(KeyPrefix prefix, String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String real = prefix.getPrefix() + key;
            return jedis.exists(real);
        }finally {
            returnToPool(jedis);
        }
    }

    public <T>Long del(KeyPrefix prefix, String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String real = prefix.getPrefix() + key;
            return jedis.del(real);
        }finally {
            returnToPool(jedis);
        }
    }
}
