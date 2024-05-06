package ru.pinchuk.urlShortener.service.impl;

import org.springframework.stereotype.Service;
import ru.pinchuk.urlShortener.repository.RedisRepository;
import ru.pinchuk.urlShortener.service.UrlService;


@Service
public class UrlServiceImpl implements UrlService {

    private final RedisRepository redisRepository;

    public UrlServiceImpl(RedisRepository redisRepository) {
        this.redisRepository = redisRepository;
    }

    @Override
    public String findByShortUrl(String shortUrl) {
        return redisRepository.findByKey(shortUrl);
    }

    @Override
    public String create(String longUrl) {
        return redisRepository.save(longUrl);
    }
}
