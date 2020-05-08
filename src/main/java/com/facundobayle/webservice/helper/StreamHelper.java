package main.java.com.facundobayle.webservice.helper;

import java.io.IOException;
import java.io.InputStream;

public class StreamHelper {

    public String readLine(InputStream inputStream) throws IOException {
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

    public String readBodyLine(InputStream inputStream, int contentLength) throws IOException {
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = inputStream.read()) > 0) {
            sb.append((char) c);
            if(sb.length() == contentLength) break;
        }

        return sb.toString();
    }
}
