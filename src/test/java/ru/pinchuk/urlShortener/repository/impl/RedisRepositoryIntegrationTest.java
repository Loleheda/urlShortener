package ru.pinchuk.urlShortener.repository.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RedisRepositoryIntegrationTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private RedisRepositoryImpl redisRepository;

    @BeforeEach
    void setUp() {
        redisRepository = new RedisRepositoryImpl(stringRedisTemplate);
    }

    @AfterEach
    void afterAll() {
        // Удаляем все ключи из Redis после каждого теста
        stringRedisTemplate.getConnectionFactory().getConnection().flushAll();
    }

    @Test
    void findByKeyTest() {
        // Arrange
        String value = "test-value";
        String key = redisRepository.save(value);

        // Act
        String retrievedValue = redisRepository.findByKey(key);

        // Assert
        assertEquals(value, retrievedValue);
    }

    @Test
    void saveTest() {
        // Arrange
        String value = "test-value";

        // Act
        String key1 = redisRepository.save(value);
        String key2 = redisRepository.save(value);

        // Assert
        String retrievedValue = stringRedisTemplate.opsForValue().get(key1);
        assertEquals(value, retrievedValue);
        assertEquals(key1, key2);
    }
}