package com.facundobayle.webservice.client;

import com.facundobayle.webservice.server.configuration.ServerConfig;
import com.facundobayle.webservice.server.Server;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class App {

    public static void main(String[] args) {
        var configuration = new ServerConfig(9290, 1, Duration.of(30, ChronoUnit.SECONDS));
        try {
            var servlet = new AppServlet();
            new Server(configuration, servlet).start();
        } catch (IOException e) {
            System.out.println("Error starting Server.");
            e.printStackTrace();
        }
    }
}

