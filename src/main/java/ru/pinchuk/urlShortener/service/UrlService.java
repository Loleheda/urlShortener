package ru.pinchuk.urlShortener.service;


public interface UrlService {

    /**
     * Находит длинный URL, соответствующий переданному короткому URL.
     *
     * @param shortUrl Короткий URL для поиска.
     * @return Длинный URL, соответствующий предоставленному короткому URL, или null, если совпадение не найдено.
     */
    String findByShortUrl(String shortUrl);

    /**
     * Создает короткий URL на основе предоставленного длинного URL.
     *
     * @param  longUrl Длинный URL, для которого создается короткий URL.
     * @return Сгенерированный короткий URL.
     */
    String create(String longUrl);
}