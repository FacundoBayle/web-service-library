package com.facundobayle.webservice.formatter;

import com.facundobayle.webservice.model.response.HttpResponse;
import com.facundobayle.webservice.model.response.HttpStatus;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HttpResponseFormatter {

    public HttpResponse parseResponse() {
        return new HttpResponse(HttpStatus.OK,
                Map.of("Content-Type:", List.of("text/html"),
                        "X-DESP-SANDBOX", List.of("true")),
                Optional.of("response body"));
    }
}
