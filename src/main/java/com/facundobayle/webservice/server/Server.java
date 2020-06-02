package com.facundobayle.webservice.server;

import com.facundobayle.webservice.handler.ConnectionRejectedHandler;
import com.facundobayle.webservice.handler.HttpResponseHandler;
import com.facundobayle.webservice.worker.WorkerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

public class Server {

    private ServerConfig serverConfig;

    public Server(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    public void start() throws IOException {
        var server = new ServerSocket(
                serverConfig.getPort());
        var rejectHandler = new ConnectionRejectedHandler(new HttpResponseHandler());
        ExecutorService executorService = new WorkerFactory()
                .create(serverConfig.getWorkers(), serverConfig.getKeepAlive());
        var count = 0;
        System.out.println("Server Created.");


        while(true) {
            try {
                var socket = server.accept();
                System.out.println("Nuevo cliente.");
                var socketHandler = new SocketHandler(socket);
                try {
                    executorService.execute(socketHandler);
                    count+=1;
                    System.out.println(count);
                } catch (RejectedExecutionException e) {
                    rejectHandler.reject(socket);
                    socket.close();
                }

            } catch (IOException e) {
                System.out.println("Error listening to connection: " + e.getMessage());
            }
        }
    }
}
