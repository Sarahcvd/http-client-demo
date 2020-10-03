package no.kristiania.httpclient;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HttpMessage {
    private final String startLine;
    private final Map<String, String> headers;
    private final String body;

    public HttpMessage(Socket socket) throws IOException {
        startLine = readLine(socket);
        headers = readHeaders(socket);
        String contentLength = headers.get("Content-Length");
        if (contentLength != null){
            body = readBody(socket, Integer.parseInt(contentLength));
        }else{
            body = null;
        }
    }

    public static String readLine(Socket socket) throws IOException {
        // Reads one BYTE at a time, until there is nothing more to read
        // (c = socket-getInputStream().read()) != -1 means
        // Assign the next value of "read()" to c and check if it's not -1
        // (-1 means end of data)
        StringBuilder line = new StringBuilder();
        int c;
        while((c = socket.getInputStream().read()) != -1){
            // Stop reading at newline
            if(c == '\r'){
                socket.getInputStream().read();
                break;
            }
            // Treat each byte as a character ("(char)") and print it to the console
            line.append((char)c);
        }
        return line.toString();
    }

    static String readBody(Socket socket, int contentLength) throws IOException {
        StringBuilder body = new StringBuilder();
        for (int i = 0; i < contentLength; i++) {
            body.append((char) socket.getInputStream().read());
        }
        return body.toString();
    }

    static Map<String, String> readHeaders(Socket socket) throws IOException {
        Map<String, String> headers = new HashMap<>();
        String headerLine;
        while(!(headerLine = readLine(socket)).isEmpty()){
            System.out.println(headerLine);
            int colonPos = headerLine.indexOf(':');
            String headerName = headerLine.substring(0, colonPos);
            String headerValue = headerLine.substring(colonPos+1).trim();

            headers.put(headerName, headerValue);
        }
        return headers;
    }

    public String getStartLine() {
        return startLine;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
}
