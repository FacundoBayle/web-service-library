package com.facundobayle.webservice.server.model.response;

import com.facundobayle.webservice.jdk.ServletResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HttpResponse implements ServletResponse {
    private HttpStatus status;
    private Map<String, List<String>> headers;
    private Optional<String> body;

    public HttpResponse(HttpStatus status, Map<String, List<String>> headers, Optional<String> body) {
        this.status = status;
        this.headers = headers;
        this.body = body;
    }



    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    @Override
    public int getStatusCode() {
        return 0;
    }

    public Optional<String> getBody() {
        return body;
    }

    @Override
    public Optional<List<String>> getHeaderValues(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<String> getHeader(String name) {
        return Optional.empty();
    }

    @Override
    public void setStatusCode(int statusCode) {

    }

    @Override
    public void addHeaderValues(String name, List<String> values) {

    }

    @Override
    public void addHeader(String name, String values) {

    }

    public void setBody(Optional<String> body) {
        this.body = body;
    }
}
