package com.facundobayle.webservice.jdk;

public interface Servlet {
    void dispatch(ServletRequest request, ServletResponse response);
}