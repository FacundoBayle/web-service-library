package com.facundobayle.webservice.jdk;

import java.util.List;
import java.util.Optional;

public interface ServletRequest {

    String getHost();

    String getMethod();

    Optional<String> getHeader(String name);

    Optional<List<String>> getHeaderValues(String name);

    String getPath();

    Optional<String> getBody();

    Optional<String> getQueryString();

}

