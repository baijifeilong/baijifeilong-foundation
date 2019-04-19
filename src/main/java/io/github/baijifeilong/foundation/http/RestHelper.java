package io.github.baijifeilong.foundation.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BaiJiFeiLong@gmail.com at 2019-04-19 10:55
 */
@SuppressWarnings({"unused", "WeakerAccess"})
@Slf4j
@Component
public class RestHelper {
    private OkHttpClient okHttpClient = new OkHttpClient();
    private ObjectMapper objectMapper = new ObjectMapper();

    public JsonNode doGet(String url) {
        return doGet(url, new HashMap<>());
    }

    public JsonNode doGet(String url, Map<String, String> params) {
        HttpUrl.Builder httpUrlBuilder = HttpUrl.get(url).newBuilder();
        params.forEach(httpUrlBuilder::addQueryParameter);
        HttpUrl httpUrl = httpUrlBuilder.build();
        Request request = new Request.Builder().url(httpUrl).build();
        return executeRequest(request);
    }

    public JsonNode doPost(String url, Map<String, String> params) {
        return doSubmit("POST", url, params);
    }

    public JsonNode doPatch(String url, Map<String, String> params) {
        return doSubmit("PATCH", url, params);
    }

    public JsonNode doPut(String url, Map<String, String> params) {
        return doSubmit("PUT", url, params);
    }

    public JsonNode doDelete(String url) {
        Request request = new Request.Builder().url(url).delete().build();
        return executeRequest(request);
    }

    private JsonNode doSubmit(String method, String url, Map<String, String> params) {
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        params.forEach(formBodyBuilder::add);
        FormBody formBody = formBodyBuilder.build();
        Request request = new Request.Builder().url(url).method(method, formBody).build();
        return executeRequest(request);
    }

    @SneakyThrows
    private JsonNode executeRequest(Request request) {
        log.info(">>> [REQUEST] {}", request);
        Response response = okHttpClient.newCall(request).execute();
        log.info("<<< [RESPONSE] {}", response);
        if (!response.isSuccessful()) {
            throw new RuntimeException(String.format("[RestHelper] %s %s", response.code(), response.message()));
        }
        assert response.body() != null;
        String text = response.body().string();
        JsonNode node = objectMapper.readValue(text, JsonNode.class);
        log.info("<<< [RESPONSE] [JSON] {}", node);
        return node;
    }
}
