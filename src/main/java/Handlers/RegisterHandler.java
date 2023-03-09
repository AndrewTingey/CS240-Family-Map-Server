package Handlers;


import Requests.RegisterRequest;
import Results.RegisterResult;
import Services.RegisterService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;


//this does not work!!

public class RegisterHandler implements HttpHandler {
    @Override
    public void handle( HttpExchange exchange ) throws IOException {
        Gson gson = new Gson();

        Reader reqBody = new InputStreamReader(exchange.getRequestBody());
        RegisterRequest request = (RegisterRequest) gson.fromJson(reqBody, RegisterRequest.class);

        RegisterResult result = new RegisterService().register(request);

        Writer resbody = new OutputStreamWriter(exchange.getResponseBody());
        gson.toJson(result, resbody);

        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

        resbody.close();
    }
}