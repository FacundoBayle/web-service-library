package com.facundobayle.webservice.servlet;

public interface Servlet {
    void dispatch(ServletRequest request, ServletResponse response);
}