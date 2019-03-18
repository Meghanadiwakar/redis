package com.stackroute.rediscacheservice;

import com.stackroute.rediscacheservice.model.Question;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class RedisCacheServiceApplication {
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {

        return new JedisConnectionFactory();

    }

    @Bean
    RedisTemplate<String, Question> redisTemplate() {
        RedisTemplate<String, Question> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }



    public static void main(String[] args) {
        SpringApplication.run(RedisCacheServiceApplication.class, args);
    }
}

