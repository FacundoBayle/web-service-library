package com.facundobayle.webservice.server;

import java.time.Duration;

public class ServerConfig {
    private final int port;
    private final int workers;
    private final Duration keepAlive;

    public ServerConfig(int port, int workers, Duration keepAlive) {
        this.port = port;
        this.workers = workers;
        this.keepAlive = keepAlive;
    }

    public int getPort() {
        return port;
    }

    public int getWorkers() {
        return workers;
    }

    public Duration getKeepAlive() {
        return keepAlive;
    }

}
