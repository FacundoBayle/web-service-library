package com.facundobayle.webservice.server;

import com.facundobayle.webservice.handler.HttpResponseHandler;
import com.facundobayle.webservice.formatter.HttpRequestFormatter;
import com.facundobayle.webservice.formatter.HttpResponseFormatter;
import com.facundobayle.webservice.helper.StreamHelper;

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

    public SocketHandler(Socket socket) {
        this.socket = socket;
        this.streamHelper = new StreamHelper();
        this.httpRequestFormatter = new HttpRequestFormatter();
        this.httpResponseFormatter = new HttpResponseFormatter();
        this.httpResponseHandler = new HttpResponseHandler();
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
