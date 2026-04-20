package com.example.gitinsight.ai.client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public abstract class BaseApiClient {

    protected final RestClient restClient;

    protected <T> T get(String uri, Class<T> responseType, Object... uriVariables) {
        return restClient.get()
                .uri(uri, uriVariables)
                .retrieve()
                .body(responseType);
    }
}
