package com.facundobayle.webservice.server.model.request;

import com.facundobayle.webservice.jdk.ServletRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HttpRequest implements ServletRequest {
    private HttpMethod method;
    private URL url;
    private Map<String, List<String>> headers;
    private Optional<String> body;

    public HttpRequest(HttpMethod method, URL url, Map<String, List<String>> headers, Optional<String> body) {
        this.method = method;
        this.url = url;
        this.headers = headers;
        this.body = body;
    }

    @Override
    public String getHost() {
        return null;
    }

    @Override
    public String getMethod() {
        return this.method.name();
    }

    public HttpMethod getHttpMethod() {
        return method;
    }

    @Override
    public Optional<String> getHeader(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<List<String>> getHeaderValues(String name) {
        return Optional.empty();
    }

    @Override
    public String getPath() {
        return null;
    }

    public URL getUrl() {
        return url;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public Optional<String> getBody() {
        return body;
    }

    @Override
    public Optional<String> getQueryString() {
        return Optional.empty();
    }
}
