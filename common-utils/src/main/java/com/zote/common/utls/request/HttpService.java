package com.zote.common.utls.request;

import java.util.Map;

public interface HttpService {

    <T> T get(String url, Class<T> responseType);

    <T> T get(String url, Class<T> responseType, Map<String, String> headers);

    <T> T get(String url, Class<T> responseType, Map<String, String> headers, Map<String, String> queryParams);

    <T, R> T post(String url, R request, Class<T> responseType, Map<String, String> headers);
}
