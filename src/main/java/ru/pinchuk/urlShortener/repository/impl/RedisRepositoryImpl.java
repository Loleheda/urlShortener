package ru.pinchuk.urlShortener.repository.impl;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import ru.pinchuk.urlShortener.repository.RedisRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


@Repository
public class RedisRepositoryImpl implements RedisRepository {

    private final StringRedisTemplate template;

    public RedisRepositoryImpl(StringRedisTemplate template) {
        this.template = template;
    }

    @Override
    public String findByKey(String key) {
        return template.opsForValue().get(key);
    }

    @Override
    public String save(String value) {
        String key = hashKey(value);
        if (findByKey(key) == null) {
            template.opsForValue().append(key, value);
        }
        return key;
    }

    /**
     * Хэширует входное значение с использованием алгоритма SHA-256 и возвращает первые 8 символов закодированного хэша в виде строки.
     *
     * @param value Входное значение, которое требуется захешировать.
     * @return Первые 8 символов закодированного хэша в виде строки.
     * @throws RuntimeException Если во время хэширования возникает исключение NoSuchAlgorithmException.
     */
    private String hashKey(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Хэширование входной строки
            byte[] hashBytes = digest.digest(value.getBytes());
            String encodedHash = Base64.getEncoder().encodeToString(hashBytes);
            // Взятие первых 8 символов закодированного хэша
            return encodedHash.replaceAll("[^A-z0-9]", "").substring(0, 8);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
