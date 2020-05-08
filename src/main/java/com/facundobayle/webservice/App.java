package com.facundobayle.webservice;

import com.facundobayle.webservice.server.Server;

import java.io.IOException;

public class App {

    public static void main(String[] args) {
        var server = new Server(9290);
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

