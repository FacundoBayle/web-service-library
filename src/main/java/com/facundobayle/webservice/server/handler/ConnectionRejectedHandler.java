package com.facundobayle.webservice.server.handler;

import com.facundobayle.webservice.server.model.response.Responses;

import java.io.IOException;
import java.net.Socket;

public class ConnectionRejectedHandler {

    private  HttpResponseHandler httpResponseHandler;

    public ConnectionRejectedHandler(HttpResponseHandler httpResponseHandler) {
        this.httpResponseHandler = httpResponseHandler;
    }

    public void reject(Socket socket) throws IOException {
        System.out.println("Connection rejected");
        this.httpResponseHandler.sendResponse(Responses.SERVICE_UNAVAILABLE, socket.getOutputStream());
    }
}