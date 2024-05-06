package ru.pinchuk.urlShortener.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.pinchuk.urlShortener.service.UrlService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UrlControllerTest {

    @Mock
    private UrlService urlService;

    @InjectMocks
    private UrlController urlController;

    @Test
    void redirectByShortUrlTest_LongUrlFound() {
        // Arrange
        String shortUrl = "abc123";
        String longUrl = "https://www.test.com";
        when(urlService.findByShortUrl(shortUrl)).thenReturn(longUrl);

        // Act
        ResponseEntity<String> response = urlController.redirectByShortUrl(shortUrl);

        // Assert
        assertEquals(HttpStatus.MOVED_PERMANENTLY, response.getStatusCode());
        assertEquals(longUrl, response.getHeaders().getLocation().toString());
    }

    @Test
    void redirectByShortUrlTest_LongUrlNotFound() {
        // Arrange
        String shortUrl = "abc123";
        when(urlService.findByShortUrl(shortUrl)).thenReturn(null);

        // Act
        ResponseEntity<String> response = urlController.redirectByShortUrl(shortUrl);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Long URL not found for short URL <b>" + shortUrl + "</b>", response.getBody());
    }

    @Test
    void createShortUrlTest() {
        // Arrange
        String longUrl = "https://www.test.com";
        String shortUrl = "abc123";
        when(urlService.create(longUrl)).thenReturn(shortUrl);

        // Act
        ResponseEntity<String> response = urlController.createShortUrl(longUrl);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shortUrl, response.getBody());
        assertEquals(shortUrl.length(), response.getHeaders().getContentLength());
        assertEquals("text/html", response.getHeaders().getContentType().toString());
    }
}