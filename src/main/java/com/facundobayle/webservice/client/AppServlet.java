package com.facundobayle.webservice.client;

import com.facundobayle.webservice.jdk.Servlet;
import com.facundobayle.webservice.jdk.ServletRequest;
import com.facundobayle.webservice.jdk.ServletResponse;
import com.facundobayle.webservice.server.model.HttpHeaders;

import java.util.Optional;

public class AppServlet implements Servlet {

    @Override
    public void dispatch(ServletRequest request, ServletResponse response) {
        var body = "test body";
        response.setBody(Optional.of(body));
        response.addHeader(HttpHeaders.CONTENT_TYPE, "text/plain");
        response.addHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(body.length()));
    }
}

