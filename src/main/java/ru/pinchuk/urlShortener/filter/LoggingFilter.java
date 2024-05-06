package ru.pinchuk.urlShortener.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Фильтр, который осуществляет логирование HTTP запросов.
 */
@Component
public class LoggingFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);

    /**
     * Выполняет логирование HTTP запросов и ответов.
     *
     * @param request HTTP запрос.
     * @param response HTTP ответ.
     * @param filterChain Цепочка фильтров.
     * @throws ServletException Исключение, возникающее при ошибках в сервлете.
     * @throws IOException Исключение, связанное с ошибками ввода-вывода.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String url = request.getRequestURL() + request.getQueryString();
        LOGGER.info("Начало работы контроллера с url: {}", url);
        long startTime = System.currentTimeMillis();
        filterChain.doFilter(request, response);
        LOGGER.info("Конец работы контроллера с url: {}\nВремя работы: {} ms", url, System.currentTimeMillis() - startTime);
    }
}
