package com.example.gitinsight.ai.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class BaseApiClientFactory {
    private final RestClient.Builder baseBuilder;

    public BaseApiClientFactory(RestClient.Builder baseBuilder) {
        this.baseBuilder = baseBuilder;
    }

    public RestClient create(String baseUrl, String bearerToken, Map<String, String> headers) {
        RestClient.Builder builder = baseBuilder
                .clone()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        if (headers != null) {
            headers.forEach(builder::defaultHeader);
        }

        if (StringUtils.hasText(bearerToken)) {
            builder.defaultHeaders(httpHeaders -> httpHeaders.setBearerAuth(bearerToken));
        }

        return builder.build();
    }
}
