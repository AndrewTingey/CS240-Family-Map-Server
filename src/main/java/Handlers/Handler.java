package Handlers;

import Requests.Request;
import Results.Result;
import Services.BadPasswordException;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;

public abstract class Handler implements HttpHandler {
    protected String httpMethod;
    protected boolean reqAuthtoken;
    protected boolean reqRequestBody;
    protected Request request;
    private String authToken;

    public Handler( String httpMethod, boolean reqAuthtoken, boolean requestBody, Request request ) {
        this.httpMethod = httpMethod;
        this.reqAuthtoken = reqAuthtoken;
        this.reqRequestBody = requestBody;
        this.request = request;
    }

    protected abstract Result doService( Request request, String authToken );

    @Override
    public void handle( HttpExchange exchange ) throws IOException {
        boolean success;
        try {
            if (exchange.getRequestMethod().toLowerCase().equals(httpMethod)) {
                Headers reqHeaders = exchange.getRequestHeaders();
                if (reqAuthtoken) {
                    if (reqHeaders.containsKey("Authorization")) {
                        this.authToken = reqHeaders.getFirst("Authorization");
                    } else {
                        success = false;
                        throw new BadPasswordException("Authorization required");
                    }
                }
                Gson gson = new Gson();

                if (reqRequestBody) {
                    Reader reqBody = new InputStreamReader(exchange.getRequestBody());
                    request = gson.fromJson(reqBody, request.getClass()); //does request.getClass() work?
                }
                Result result = doService(request, authToken);

                Writer resBody = new OutputStreamWriter(exchange.getResponseBody());
                if (result.isSuccess()) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                } else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                }
                gson.toJson(result, resBody);
                resBody.close();
            }
        } catch (IOException | JsonSyntaxException | JsonIOException e) {
            System.out.println("Error: " + e.getMessage());
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        } catch (BadPasswordException e) {
            Writer resBody = new OutputStreamWriter(exchange.getResponseBody());
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            Result result = new Result("Authorization required", false);
            new Gson().toJson(result, resBody);
            resBody.close();
        }
    }
}
