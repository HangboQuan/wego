package com.jd.wego.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.*;

/**
 * @author hbquan
 * @date 2021/3/30 17:23
 */
@Service
public class JedisService {

    @Autowired
    JedisPool jedisPool;


    public <T> T getKey(KeyPrefix prefix, String key, Class<T> clazz) {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            // 拼接为完整的一个Redis key
            String real = prefix.getPrefix() + key;
            String str = jedis.get(real);
            return stringToBean(str, clazz);

        } finally {
            // 将jedis连接放入连接池中
            returnToPool(jedis);
        }

    }

    public <T> Boolean setKey(KeyPrefix prefix, String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() <= 0) {
                return false;
            }
            String real = prefix.getPrefix() + key;
            if (prefix.expireSeconds() <= 0) {
                jedis.set(real, str);
            } else {
                jedis.setex(real, prefix.expireSeconds(), str);
            }
            return true;
        } finally {
            returnToPool(jedis);
        }
    }

    public <T> Boolean lpush(KeyPrefix prefix, String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() <= 0) {
                return false;
            }
            String real = prefix.getPrefix() + key;

            jedis.lpush(real, str);
            return true;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * rpop命令的阻塞版本,当给定列表内没有任何元素的弹出的时候，连接江北brpop命令阻塞，知道等待超时或者发现
     * 可弹出元素为止
     */
    public List<String> brpop(KeyPrefix prefix, String key) {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            // 拼接为完整的一个Redis key
            String real = prefix.getPrefix() + key;
            List<String> str = jedis.brpop(prefix.expireSeconds(), real);
            return str;

        } finally {
            // 将jedis连接放入连接池中
            returnToPool(jedis);
        }
    }

    /**
     * Redis的Set相关操作, 给集合中添加数据
     *
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> Boolean sadd(String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() <= 0) {
                return null;
            }
            jedis.sadd(key, str);
            return true;
        } finally {
            jedis.close();
        }
    }

    /**
     * 从Redis的Set集合中删除数据
     *
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> Boolean srem(String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() <= 0) {
                return null;
            }
            jedis.srem(key, str);
            return true;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 返回set集合中的成员
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> Set<String> smembers(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.smembers(key);
        } finally {
            jedis.close();
        }
    }

    /**
     * 模糊查询
     *
     * @param pattern
     * @param <T>
     * @return
     */
    public <T> Set<String> keys(String pattern) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.keys(pattern);
        } finally {
            jedis.close();
        }
    }

    /**
     * 从Redis中统计数据
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> long scard(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.scard(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }

    /**
     * 查询固定前缀的Key
     * @param key
     * @param <T>
     * @return
     */
    public <T> List<String> scan(String key){
        Jedis jedis = null;
        List<String> matchKeysList = new ArrayList<>();
        try{
            jedis = jedisPool.getResource();
            ScanParams scanParams = new ScanParams();
            scanParams.match(key);
            scanParams.count(Integer.MAX_VALUE);
            String cursor = "0";
            ScanResult<String> strs = jedis.scan(cursor, scanParams);
            for(String s : strs.getResult()){
                matchKeysList.add(s);
            }
            return matchKeysList;
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return matchKeysList;
    }

    /**
     * 序列化
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() < 0 || clazz == null) {
            return null;
        }
        if (int.class == clazz || Integer.class == clazz) {
            return (T) Integer.valueOf(str);
        } else if (String.class == clazz) {
            return (T) str;
        } else if (long.class == clazz || Long.class == clazz) {
            return (T) Long.valueOf(str);
        } else {
            // 转为Java对象
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    /**
     * 反序列化
     * @param value
     * @param <T>
     * @return
     */
    public static <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if ((int.class == clazz) || (Integer.class == clazz)) {
            return "" + value;
        } else if (String.class == clazz) {
            return (String) value;
        } else if (long.class == clazz || Long.class == clazz) {
            return String.valueOf(value);
        } else {
            // 将对象转为字符串
            return JSON.toJSONString(value);
        }
    }

    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    public <T> Long incr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String real = prefix.getPrefix() + key;
            return jedis.incr(real);
        } finally {
            returnToPool(jedis);
        }
    }

    public <T> Long decr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String real = prefix.getPrefix() + key;
            return jedis.decr(real);
        } finally {
            returnToPool(jedis);
        }
    }

    public <T> Boolean exists(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String real = prefix.getPrefix() + key;
            return jedis.exists(real);
        } finally {
            returnToPool(jedis);
        }
    }

    public <T> Long del(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String real = prefix.getPrefix() + key;
            return jedis.del(real);
        } finally {
            returnToPool(jedis);
        }
    }
}
