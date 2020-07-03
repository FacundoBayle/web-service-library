package com.facundobayle.webservice.server.handler;

import com.facundobayle.webservice.server.formatter.HttpRequestFormatter;
import com.facundobayle.webservice.server.formatter.HttpResponseFormatter;
import com.facundobayle.webservice.server.helper.StreamHelper;
import com.facundobayle.webservice.jdk.Servlet;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketHandler implements Runnable {
    private Socket socket;
    private HttpRequestFormatter httpRequestFormatter;
    private HttpResponseFormatter httpResponseFormatter;
    private StreamHelper streamHelper;
    private HttpResponseHandler httpResponseHandler;
    private Servlet servlet;

    public SocketHandler(Socket socket, Servlet servlet) {
        this.socket = socket;
        this.streamHelper = new StreamHelper();
        this.httpRequestFormatter = new HttpRequestFormatter();
        this.httpResponseFormatter = new HttpResponseFormatter();
        this.httpResponseHandler = new HttpResponseHandler();
        this.servlet = servlet;
    }

    @Override
    public void run() {
        try {
            var inputStream = socket.getInputStream();
            var outputStream = socket.getOutputStream();
            try {
                List<String> linesList = new ArrayList<>();
                String inputLine;
                while (!(inputLine = streamHelper.readLine(inputStream)).equals("") && (inputLine.length() > 0)) {
                    linesList.add(inputLine);
                }

                var request =  httpRequestFormatter.parseClientRequest(inputStream, linesList);
                if (request.isEmpty()) {
                    inputStream.close();
                    outputStream.close();
                    socket.close();
                    return;
                }

                var response = httpResponseFormatter.parseResponse();
                this.servlet.dispatch(request.get(), response);
                httpResponseHandler.sendResponse(response, outputStream);
                inputStream.close();
                outputStream.close();
                socket.close();

            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        } catch (IOException e) {
            System.out.println("Error getting streams: " + e.getMessage());
        }
    }
}
