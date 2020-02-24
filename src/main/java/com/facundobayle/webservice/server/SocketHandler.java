package main.java.com.facundobayle.webservice.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SocketHandler {
    private Socket socket;

    public SocketHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (var in = socket.getInputStream()) {
            try {
                var firstLine = readLine(in);
                System.out.println(firstLine);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error getting streams: " + e.getMessage());
        }
    }

    private String readLine(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        int c;
        while (((c = inputStream.read()) >= 0) && (c != 0x0a)) {
            if (c != 0x0d) {
                sb.append((char) c);
            } else {
            }
        }

        return sb.toString();
    }
}
