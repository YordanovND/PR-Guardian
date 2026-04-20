package com.example.gitinsight.ai.client;

import org.springframework.web.client.RestClient;

public abstract class BaseApiClient {

    protected final RestClient restClient;

    protected BaseApiClient(RestClient restClient) {
        this.restClient = restClient;
    }

    protected <T> T get(String uri, Class<T> responseType, Object... uriVariables) {
        return restClient.get()
                .uri(uri, uriVariables)
                .retrieve()
                .body(responseType);
    }
}
