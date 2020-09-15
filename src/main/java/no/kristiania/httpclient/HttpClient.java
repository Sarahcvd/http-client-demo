package no.kristiania.httpclient;

import java.io.IOException;
import java.net.Socket;

public class HttpClient {
    public HttpClient(String hostname, int port, String requestTarget) {

    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("urlecho.appspot.com", 80);

        // Format as specified in the HTTP specification
        // Each line is separated by \r\n (CRLF)
        // The request ends with an empty line (\r\n\r\n)
        String request = "GET /echo?status=200&body=Hello%20world! HTTP/1.1\r\n" +
                "Host: urlecho.appspot.com\r\n\r\n";
        // Writes data to the server
        socket.getOutputStream().write(request.getBytes());

        // Reads one BYTE at a time, until there is nothing more to read
        // (c = socket-getInputStream().read()) != -1 means
        // Assign the next value of "read()" to c and check if it's not -1
        // (-1 means end of data)
        int c;
        while((c = socket.getInputStream().read()) != -1){
            // Treat each byte as a character ("(char)") and print it to the console
            System.out.print((char)c);
        }
    }

    public int getResponseCode() {
        return 200;
    }
}
