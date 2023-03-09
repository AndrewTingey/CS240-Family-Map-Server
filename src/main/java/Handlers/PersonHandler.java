package Handlers;

import Results.PersonResult;
import Services.PersonService;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;

public class PersonHandler implements HttpHandler {

    @Override
    public void handle( HttpExchange exchange ) throws IOException {
        System.out.println("/person Handler called");
        boolean success = false;
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {
                Headers reqHeaders = exchange.getRequestHeaders();
                if (reqHeaders.containsKey("Authorization")) {
                    String authToken = reqHeaders.getFirst("Authorization");

                    Gson gson = new Gson();

                    PersonResult result = new PersonService().person(authToken);
                    Writer respBody = new OutputStreamWriter(exchange.getResponseBody());
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    gson.toJson(result, respBody);
                    respBody.close();
                    success = true;
                }
            }
            if (!success) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                exchange.getResponseBody().close();
            }
        } catch (JsonIOException | IOException e) {
            System.out.println("Error: " + e.getMessage());
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }
}
