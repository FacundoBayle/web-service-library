package com.facundobayle.webservice.formatter;

import com.facundobayle.webservice.helper.StreamHelper;
import com.facundobayle.webservice.model.request.HttpMethod;
import com.facundobayle.webservice.model.request.HttpRequest;
import com.facundobayle.webservice.model.request.Protocol;
import com.facundobayle.webservice.model.request.URL;

import java.io.*;
import java.util.*;


public class HttpRequestFormatter {

    private static final String HOST = "Host";
    private static final String CONTENT_LENGTH = "Content-Length";

    private StreamHelper streamHelper;

    public HttpRequestFormatter() {
        this.streamHelper = new StreamHelper();
    }

    public Optional<HttpRequest> parseClientRequest(InputStream inputStream, List<String> requestLinesList) throws IOException {
        if (requestLinesList.isEmpty()) return Optional.empty();

        var firstLine = requestLinesList.get(0);
        var tokenize = new StringTokenizer(firstLine);
        var method = HttpMethod.valueOf(tokenize.nextToken().toUpperCase());

        requestLinesList.remove(0);
        var headers = buildHeaders(requestLinesList);

        var url = buildUrl(tokenize.nextToken(), headers);

        String body = null;
        if (requestHasBody(headers)) {
            var contentlength = Integer.parseInt(headers.get(CONTENT_LENGTH).get(0).trim());
            body = buildBody(inputStream, contentlength);
        }

        return Optional.of(new HttpRequest(method, url, headers, Optional.ofNullable(body)));
    }


    private URL buildUrl(String fullPath, Map<String, List<String>> headers) {
        String host = headers.get(HOST).get(0);
        Map<String, String> queryParams = new HashMap<>();
        var splittedFullPath = fullPath.split("\\?", 2);

        if (splittedFullPath.length > 1) {
            var queryParamsString = splittedFullPath[1];
            var queryParamList = List.of(queryParamsString.split("&"));
            queryParamList.forEach(qp -> {
                var kv = qp.split("=", 2);
                queryParams.put(kv[0], kv[1]);
            });
        }

        //preguntar si las validaciones (por ejemplo devolver 400 con request sin header host),
        // (por ejemplo si viene un http method que no soportamos), etc
        // las agregamos por nuestra cuenta o hay un capitulo dedicado a eso.
        //preguntar si el path que tiene la request tiene que ser con los query params
        return new URL(Protocol.HTTP, host, fullPath, queryParams);
    }

    private Map<String, List<String>> buildHeaders(List<String> requestlinesList) {
        Map<String, List<String>> headers = new HashMap<>();

        requestlinesList
                .forEach(line -> {
                    var kv = line.split(":", 2);
                    var values = List.of(kv[1].split(","));
                    headers.put(kv[0], values);
                });

        return headers;
    }

    private String buildBody(InputStream inputStream, int contentLength) throws IOException {
        try {
            var bodyLine = streamHelper.readBodyLine(inputStream, contentLength);
            return bodyLine;
        } catch (IOException ex) {
            throw ex;
        }
    }

    private boolean requestHasBody(Map<String, List<String>> headers) {
        return headers.containsKey(CONTENT_LENGTH);
    }
}
