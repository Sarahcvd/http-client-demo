package no.kristiania.httpclient;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HttpClientTest {

    @Test
    void shouldReadSuccessStatusCode() throws IOException {
        HttpClient httpClient = makeEchoRequest("/echo?status=200");
        assertEquals(200, httpClient.getStatusCode());
    }

    private HttpClient makeEchoRequest(String requestTarget) throws IOException {
        return new HttpClient("urlecho.appspot.com", 80, requestTarget);
    }

    @Test
    void shouldReadFailureStatusCode() throws IOException {
        HttpClient httpClient = makeEchoRequest("/echo?status=401");
        assertEquals(401, httpClient.getStatusCode());
    }

    @Test
    void shouldReadHeaders() throws IOException {
        HttpClient httpClient = makeEchoRequest("/echo?body=Kristiania");
        assertEquals("10", httpClient.getResponseHeader("Content-Length"));
    }
}