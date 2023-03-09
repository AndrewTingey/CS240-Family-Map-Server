package Handlers;

import Requests.LoadRequest;
import Results.LoadResult;
import Services.LoadService;
import com.google.gson.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import Requests.LoadRequest;

import java.io.*;
import java.net.HttpURLConnection;

public class LoadHandler implements HttpHandler {
    @Override
    public void handle( HttpExchange exchange ) throws IOException {
        Gson gson = new Gson();

        Reader reqBody = new InputStreamReader(exchange.getRequestBody());
        LoadRequest request = (LoadRequest) gson.fromJson(reqBody, LoadRequest.class);

        LoadResult response = new LoadService().load(request);

        Writer resBody = new OutputStreamWriter(exchange.getResponseBody());
        gson.toJson(response, resBody);

        if (!response.isSuccess()) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);

        } else {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        }
        resBody.close();
    }
}
