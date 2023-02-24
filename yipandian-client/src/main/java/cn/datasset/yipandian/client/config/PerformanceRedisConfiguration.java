package cn.datasset.yipandian.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class PerformanceRedisConfiguration extends AbstractRedisConfiguration {
    @Bean("redisStringTemplate")
    public RedisTemplate redisTemplate(@Value("${spring.redis.host}") String hostName,
                                       @Value("${spring.redis.port}") int port,
                                       @Value("${spring.redis.password}") String password,
                                       @Value("${spring.redis.testOnBorrow}") boolean testOnBorrow,
                                       @Value("${spring.redis.jedis.pool.min-idle}") int maxIdle,
                                       @Value("${spring.redis.jedis.pool.max-active}") int maxTotal,
                                       @Value("${spring.redis.database}") int index,
                                       @Value("${spring.redis.jedis.pool.max-wait}") long maxWaitMillis/*,
	                                         @Value("${spring.redis.timeout}") int timeout*/) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(new JedisConnectionFactory());
        template.setKeySerializer(RedisSerializer.string());
        template.setValueSerializer(RedisSerializer.json());
        template.setHashKeySerializer(RedisSerializer.string());
        template.setHashValueSerializer(RedisSerializer.json());
        template.afterPropertiesSet();
        template.setConnectionFactory(
                connectionFactory(hostName, port, password, maxIdle, maxTotal, index, maxWaitMillis, testOnBorrow));

        return template;
    }
}
