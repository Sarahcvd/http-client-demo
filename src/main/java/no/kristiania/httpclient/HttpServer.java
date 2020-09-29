package no.kristiania.httpclient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    public HttpServer(int port) throws IOException {
        // Open an entry point to our program for network clients
        ServerSocket serverSocket = new ServerSocket(port);

        // New threads executes the code in a separate "thread", that is: In parallel
        new Thread(() -> {  // Anonymous function with code that will be executed in parallel (INFINITE LOOP!!)
            try {
                // Accept waits for a client to try and connect - blocks until a connection is successful
                Socket clientSocket = serverSocket.accept();
                handleRequest(clientSocket);
            }catch (IOException e) {
                // If something went wrong with the connection - print out exception and try again
                e.printStackTrace();
            }
        }).start();  // Start the threads, so the code inside executes without blocking the current thread
                     // Now the test does NOT have to wait for someone to connect
    }

    // This code will be executed for each client (connection)
    private static void handleRequest(Socket clientSocket) throws IOException {
        String requestLine = HttpClient.readLine(clientSocket);
        System.out.println(requestLine);
        // Example "GET /echo?body=hello HTTP/1.1"  (this is what the browser writes)

        String requestTarget = requestLine.split(" ")[1];
        // Example "GET /echo?body=hello"
        String statusCode = "200";


        int questionPos = requestTarget.indexOf('?');
        // Looking for query-parts (if (query-parts) );
        if(questionPos != -1){
            // "body=hello"
            QueryString queryString = new QueryString(requestTarget.substring(questionPos+1));
            statusCode = queryString.getParameter("status");
        }

        String response = "HTTP/1.1 " + statusCode + " OK\r\n" +
                "Content-Type: text/html; charset=utf-8\r\n" +
                "Content-Length: 11\r\n" +
                "\r\n" +
                "Hello World";

        // Write the response back to the client
        clientSocket.getOutputStream().write(response.getBytes());
    }

    public static void main(String[] args) throws IOException {
        new HttpServer(8080);
    }
}
