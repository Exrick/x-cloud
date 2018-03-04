package cn.exrick.xcloud.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import java.io.Serializable;

/**
 * @author Exrickx
 */
@Slf4j
@Configuration
@EnableCaching
public class RedisCachingConfig extends CachingConfigurerSupport {

    @Configuration
    static class LocalConfiguration {

        @Bean
        public CacheManager cacheManager(RedisTemplate<?,?> redisTemplate) {
            CacheManager cacheManager = new RedisCacheManager(redisTemplate);
            return cacheManager;
        }

        @Bean
        public RedisTemplate<Serializable, Serializable> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

            RedisTemplate<Serializable, Serializable> redisTemplate = new RedisTemplate<Serializable, Serializable>();
            //key序列化方式, 避免出现乱码
            Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
            redisTemplate.setKeySerializer(jackson2JsonRedisSerializer);
            redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
            redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
            redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());

            redisTemplate.setConnectionFactory(redisConnectionFactory);
            return redisTemplate;
        }
    }

    /**
     * 自定义键key生成策略: 包名+方法名+参数列表
     * @return
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(":" + method.getName() + ":");
            for (Object obj : objects) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }
}
