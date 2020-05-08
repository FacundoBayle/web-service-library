package com.facundobayle.webservice;

import com.facundobayle.webservice.model.response.HttpResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.util.stream.Collectors;

public class HttpResponseHandler {

    private static final String HTTP1_1 = "HTTP/1.1 ";
    private static final String CR_LF = "\r\n";

    public void sendResponse(HttpResponse httpResponse, OutputStream output) throws IOException {
        var code = httpResponse.getStatus().getCode();
        var statusDescription = httpResponse.getStatus().getDescription();
        var headers = httpResponse.getHeaders();
        var body = httpResponse.getBody();

        String response =
                HTTP1_1 + code + " " + statusDescription + CR_LF +
                        headers.entrySet().stream()
                                .map(header -> header.getKey() + ": " + String.join(", ", header.getValue()) + CR_LF)
                                .collect(Collectors.joining()) +
                        CR_LF + CR_LF + body.orElse("") + CR_LF + CR_LF;

        byte[] s = response.getBytes();
        output.write(s);
    }
}
