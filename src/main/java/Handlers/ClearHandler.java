package Handlers;

import Results.ClearResult;
import Services.ClearService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.nio.file.Files;

public class ClearHandler implements HttpHandler {
    @Override
    public void handle( HttpExchange exchange ) throws IOException {
        ClearResult result = new ClearService().clear();
        if (!result.isSuccess()) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            System.out.println(result.getMessage());
        } else {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        }

        Writer resBody = new OutputStreamWriter(exchange.getResponseBody());
        Gson gson = new Gson();
        gson.toJson(result, resBody);
        resBody.close();
    }
}
