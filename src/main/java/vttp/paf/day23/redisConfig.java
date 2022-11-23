package vttp.paf.day23;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import vttp.paf.day23.model.breweries;

@Configuration
public class redisConfig {
    
    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    //@Value("${spring.redis.password}") TODO change when working LOCAL
    private String redisPassword = "weishunlim";

    @Value("${spring.redis.database}")
    private int redisDB;

    @Bean(name = "RedisTemplate")
    @Scope("singleton")
    public RedisTemplate <String, breweries> redisTemplate(){
        final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        System.out.println("REDIS DETAILS >>> "+ redisHost + redisPassword + redisPort + redisDB);

        config.setHostName(redisHost);
        config.setPassword(redisPassword);
        config.setPort(redisPort);
        config.setDatabase(redisDB);

        final JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();
        final JedisConnectionFactory jedisfac = new JedisConnectionFactory(config, jedisClient);
        jedisfac.afterPropertiesSet();

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(breweries.class);
        RedisTemplate<String, breweries> template = new RedisTemplate<String, breweries>();
        template.setConnectionFactory(jedisfac);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(jackson2JsonRedisSerializer);  
        return template;
    }

}
