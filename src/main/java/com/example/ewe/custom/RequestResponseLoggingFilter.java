package com.example.ewe.custom;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestResponseLoggingFilter implements Filter {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger log = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(httpReq);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(httpRes);

        long start = System.currentTimeMillis();
        chain.doFilter(wrappedRequest, wrappedResponse);
        long duration = System.currentTimeMillis() - start;

        logRequest(wrappedRequest);
        logResponse(wrappedResponse, duration);

        wrappedResponse.copyBodyToResponse(); // required
    }

    private void logRequest(ContentCachingRequestWrapper request) throws IOException {
        Map<String, Object> logMap = new LinkedHashMap<>();
        logMap.put("type", "http");
        logMap.put("direction", "incoming");
        logMap.put("method", request.getMethod());
        logMap.put("uri", request.getRequestURI());
        logMap.put("headers", Collections.list(request.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(h -> h, request::getHeader)));

        byte[] buf = request.getContentAsByteArray();
        if (buf.length > 0) {
            logMap.put("body", tryParseJson(buf));
        }

        log.info(objectMapper.writeValueAsString(logMap));
    }

    private void logResponse(ContentCachingResponseWrapper response, long duration) throws IOException {
        Map<String, Object> logMap = new LinkedHashMap<>();
        logMap.put("type", "http");
        logMap.put("direction", "outgoing");
        logMap.put("status", response.getStatus());
        logMap.put("durationMs", duration);
        logMap.put("headers", response.getHeaderNames()
                .stream()
                .collect(Collectors.toMap(h -> h, response::getHeader)));

        byte[] buf = response.getContentAsByteArray();
        if (buf.length > 0) {
            logMap.put("body", tryParseJson(buf));
        }

        log.info(objectMapper.writeValueAsString(logMap));
    }

    private Object tryParseJson(byte[] bytes) {
        try {
            return objectMapper.readValue(bytes, Object.class); // parsed as JSON
        } catch (Exception e) {
            return new String(bytes, StandardCharsets.UTF_8); // fallback to plain text
        }
    }
}
