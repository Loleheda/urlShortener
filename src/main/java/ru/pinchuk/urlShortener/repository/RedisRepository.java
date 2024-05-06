package ru.pinchuk.urlShortener.repository;


public interface RedisRepository {

    /**
     * Сохраняет значение в Redis.
     *
     * @param value Значение, которое нужно сохранить.
     * @return Ключ, по которому значение было сохранено.
     */
    String save(String value);

    /**
     * Ищет значение по ключу в Redis.
     *
     * @param key Ключ, по которому нужно найти значение.
     * @return Найденное значение или null, если совпадений не найдено.
     */
    String findByKey(String key);
}
