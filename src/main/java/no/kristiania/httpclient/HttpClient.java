package no.kristiania.httpclient;

import java.io.IOException;
import java.net.Socket;

public class HttpClient {
    private final int responseCode;

    public HttpClient(String hostname, int port, String requestTarget) throws IOException {
        Socket socket = new Socket(hostname, port);

        // Format as specified in the HTTP specification
        // Each line is separated by \r\n (CRLF)
        // The request ends with an empty line (\r\n\r\n)
        String request = "GET " + requestTarget + " HTTP/1.1\r\n" +
                "Host: " + hostname + "\r\n\r\n";
        // Writes data to the server
        socket.getOutputStream().write(request.getBytes());

        // Reads one BYTE at a time, until there is nothing more to read
        // (c = socket-getInputStream().read()) != -1 means
        // Assign the next value of "read()" to c and check if it's not -1
        // (-1 means end of data)
        StringBuilder line = new StringBuilder();
        int c;
        while((c = socket.getInputStream().read()) != -1){
            // Stop reading at newline
            if(c == '\n'){
                break;
            }
            // Treat each byte as a character ("(char)") and print it to the console
            line.append((char)c);
        }
        System.out.println(line);
        String[] responseLineParts = line.toString().split(" ");
        responseCode = Integer.parseInt(responseLineParts[1]);
    }

    public static void main(String[] args) throws IOException {
        String hostname = "urlecho.appspot.com";
        int port = 80;
        String requestTarget = "/echo?status=200&body=Hello%20world!";
        new HttpClient(hostname, port, requestTarget);
    }

    public int getResponseCode() {
        return responseCode;
    }
}
