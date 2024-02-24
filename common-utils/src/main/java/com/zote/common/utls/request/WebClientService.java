package com.zote.common.utls.request;

import com.zote.common.utls.exceptions.ErrorMessage;
import com.zote.common.utls.exceptions.FunctionalError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
@Slf4j
public class WebClientService implements HttpService{

    @Override
    public <T> T get(String url, Class<T> responseType) {
        log.info("consuming get api");
        return WebClient.create()
                .get()
                .uri(url)
                .retrieve()
                .onStatus(httpStatusCode -> !httpStatusCode.is2xxSuccessful(), clientResponse -> {
                    log.info("Error occurred while consuming http request");
                    var error = clientResponse.bodyToMono(ErrorMessage.class);
                    return error.map(errorMessage -> {
                        log.info("logged error is {}", errorMessage);
                        return new FunctionalError(errorMessage.message());
                    });
                })
                .bodyToMono(responseType)
                .block();
    }

    @Override
    public <T> T get(String url, Class<T> responseType, Map<String, String> headers) {
        return WebClient.create()
                .get()
                .uri(url)
                .headers(httpHeaders -> httpHeaders.addAll(httpHeaders))
                .retrieve()
                .onStatus(httpStatusCode -> !httpStatusCode.is2xxSuccessful(), clientResponse -> {
                    log.info("Error occurred while consuming http request");
                    var error = clientResponse.bodyToMono(ErrorMessage.class);
                    return error.map(errorMessage -> {
                        log.info("logged error is {}", errorMessage);
                        return new FunctionalError(errorMessage.message());
                    });
                })
                .bodyToMono(responseType)
                .block();
    }

    @Override
    public <T> T get(String url, Class<T> responseType, Map<String, String> headers, Map<String, String> queryParams) {
        return null;
    }

    @Override
    public <T, R> T post(String url, R request, Class<T> responseType, Map<String, String> headers) {
        return null;
    }
}
