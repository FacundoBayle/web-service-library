package com.facundobayle.webservice;

import com.facundobayle.webservice.server.Server;
import com.facundobayle.webservice.server.ServerConfig;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class App {

    public static void main(String[] args) {
        var configuration = new ServerConfig(9290, 1, Duration.of(30, ChronoUnit.SECONDS));
        try {
            new Server(configuration).start();
        } catch (IOException e) {
            System.out.println("Error starting Server.");
            e.printStackTrace();
        }
    }
}

