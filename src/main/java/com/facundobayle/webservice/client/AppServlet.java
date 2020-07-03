package com.facundobayle.webservice.client;

import com.facundobayle.webservice.servlet.Servlet;
import com.facundobayle.webservice.servlet.ServletRequest;
import com.facundobayle.webservice.servlet.ServletResponse;

import java.util.Optional;

public class AppServlet implements Servlet {
    private static final String CONTENT_LENGTH = "Content-Type";
    private static final String CONTENT_TYPE = "Content-Length";

    @Override
    public void dispatch(ServletRequest request, ServletResponse response) {
        var body = "test body";
        response.setBody(Optional.of(body));
        response.addHeader(CONTENT_TYPE, "text/plain");
        response.addHeader(CONTENT_LENGTH, String.valueOf(body.length()));
    }
}

