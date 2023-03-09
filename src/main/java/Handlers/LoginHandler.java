package Handlers;

import Requests.LoginRequest;
import Results.LoginResult;
import Services.LoginService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;

public class LoginHandler implements HttpHandler {
    @Override
    public void handle( HttpExchange exchange ) throws IOException {
        Gson gson = new Gson();

        Reader reqBody = new InputStreamReader(exchange.getRequestBody());
        LoginRequest request = (LoginRequest) gson.fromJson(reqBody, LoginRequest.class);

        LoginResult result = new LoginService().login(request);

        Writer respBody = new OutputStreamWriter(exchange.getResponseBody());
        gson.toJson(result, respBody);

        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

        respBody.close();
    }
}