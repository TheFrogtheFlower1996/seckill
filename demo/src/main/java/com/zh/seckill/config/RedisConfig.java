package com.zh.seckill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author zh
 * @date 2022/4/21 10:05
 * @description:说明 Redis配置类
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        //配置redis规则

        //key value 序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer()); //String类型
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer()); //JSON格式

        //redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Object>()); 需要传对象

        //hash key value 序列化
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());


        //注入连接工厂
        redisTemplate.setConnectionFactory(connectionFactory);
        return  redisTemplate;
    }
}
