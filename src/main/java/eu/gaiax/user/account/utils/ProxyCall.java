package eu.gaiax.user.account.utils;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;

public class ProxyCall {
    public static <T> ResponseEntity<T> doProxyCall(final WebClient srv, final HttpServletRequest request) {
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        request.getParameterMap().forEach((s, strings) -> queryParams.addAll(s, List.of(strings)));

        final WebClient.RequestHeadersSpec<?> callBuilder = srv
                .get()
                .uri(builder ->
                        builder.path(request.getRequestURI())
                                .queryParams(queryParams).build());

        final Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            final String hn = headerNames.nextElement();
            callBuilder.header(hn, request.getHeader(hn));
        }

        final String body = extractRequestBody(request);
        if (!body.isEmpty()) {
            throw new AssertionError("In GET request body is supplied");
        }

        return callBuilder.retrieve()
                .toEntity(new ParameterizedTypeReference<T>() {
                }).block();

    }

    public static <T, R> ResponseEntity<T> doPost(WebClient srv, HttpServletRequest request, R rqBody) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        request.getParameterMap().forEach((s, strings) -> queryParams.addAll(s, List.of(strings)));

        WebClient.RequestBodySpec prep = srv
                .post()
                .uri(builder ->
                        builder.path(request.getRequestURI())
                                .queryParams(queryParams).build());

        WebClient.RequestHeadersSpec<?> callBuilder = prep;
        if (rqBody != null) {
            callBuilder = prep.bodyValue(rqBody);
        }

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String hn = headerNames.nextElement();
            String header = request.getHeader(hn);
            callBuilder.header(hn, header);
        }
        return callBuilder
                .retrieve()
                .toEntity(new ParameterizedTypeReference<T>() {
                })
                .block();
    }

    public static <T, R> ResponseEntity<T> doPut(WebClient srv, HttpServletRequest request, R rqBody) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        request.getParameterMap().forEach((s, strings) -> queryParams.addAll(s, List.of(strings)));

        WebClient.RequestBodySpec prep = srv
                .put()
                .uri(builder ->
                        builder.path(request.getRequestURI())
                                .queryParams(queryParams).build());

        WebClient.RequestHeadersSpec<?> callBuilder = prep;
        if (rqBody != null) {
            callBuilder = prep.bodyValue(rqBody);
        }

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String hn = headerNames.nextElement();
            String header = request.getHeader(hn);
            callBuilder.header(hn, header);
        }
        return callBuilder
                .retrieve()
                .toEntity(new ParameterizedTypeReference<T>() {
                })
                .block();
    }

    public static <T> ResponseEntity<T> doDelete(WebClient srv, HttpServletRequest request) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        request.getParameterMap().forEach((s, strings) -> queryParams.addAll(s, List.of(strings)));

        WebClient.RequestHeadersSpec prep = srv
                .delete()
                .uri(builder ->
                        builder.path(request.getRequestURI())
                                .queryParams(queryParams).build());

        WebClient.RequestHeadersSpec<?> callBuilder = prep;

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String hn = headerNames.nextElement();
            String header = request.getHeader(hn);
            callBuilder.header(hn, header);
        }
        return callBuilder
                .retrieve()
                .toEntity(new ParameterizedTypeReference<T>() {
                })
                .block();
    }

    private static String extractRequestBody(HttpServletRequest request) {
        Scanner s = null;
        try {
            s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s.hasNext() ? s.next() : "";
    }
}