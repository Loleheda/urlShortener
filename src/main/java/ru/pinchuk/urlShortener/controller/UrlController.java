package ru.pinchuk.urlShortener.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pinchuk.urlShortener.service.UrlService;

import java.net.URI;

@RestController
@RequestMapping("/")
public class UrlController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrlController.class);

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    /**
     * Направляет на URL адрес по короткой ссылке.
     *
     * @param shortUrl Короткий URL.
     * @return Ответ с перенаправлением на URL или сообщение об ошибке, если URL не найден.
     */
    @GetMapping("/s/{shortUrl}")
    public ResponseEntity<String> redirectByShortUrl(@PathVariable String shortUrl) {
        String longUrl = urlService.findByShortUrl(shortUrl);
        if (longUrl == null) {
            LOGGER.error("\tURL не найден");
            return new ResponseEntity<>(
                    "Данный короткий URL <b>" + shortUrl + "</b> не найден",
                    HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(longUrl));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    /**
     * Создаюет короткую ссылку на основе URL адреса.
     *
     * @param longUrl Длинный URL, для которого требуется создать короткую ссылку.
     * @return Ответ с созданным коротким URL.
     */
    @GetMapping("/a")
    public ResponseEntity<String> createShortUrl(@RequestParam(name = "url") String longUrl) {
        String shortUrl = urlService.create(longUrl);
        return ResponseEntity
                .ok()
                .contentLength(shortUrl.length())
                .header("Content-type", "text/html")
                .body(shortUrl);
    }
}
