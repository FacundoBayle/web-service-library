package com.facundobayle.webservice.server.model.request;

import java.util.Map;

public class URL {
    private Protocol protocol;
    private String host;
    private String path;
    private Map<String, String> queryParams;

    public URL(Protocol protocol, String host, String path, Map<String, String> queryParams) {
        this.protocol = protocol;
        this.host = host;
        this.path = path;
        this.queryParams = queryParams;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public String getHost() {
        return host;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }
}
