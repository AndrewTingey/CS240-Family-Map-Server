package Handlers;

import Requests.FillRequest;
import Results.Result;
import Services.FillService;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;

import static java.lang.Integer.parseInt;

public class FillHandler implements HttpHandler {
    @Override
    public void handle( HttpExchange exchange ) throws IOException {
        //2 cases here:
        //  /fill/[username]/{generations}
        //  /fill/[username]

        System.out.println("Fill Handler called");
        String URI = exchange.getRequestURI().toString();
        String[] args = URI.split("/");
        try {
            if (exchange.getRequestMethod().equalsIgnoreCase("post")) {
                Gson gson = new Gson();
                Result result = new Result("Error FH.32", false);
                int generations = 0;
                if (args.length == 3) {
                    //generations = 4 by default
                    generations = 4;
                } else if (args.length == 4) { // "" / "fill" / "username" / "generations"
                    generations = parseInt(args[3]);
                } else {
                    //error wrong amount of arguments
                }

                FillRequest request = new FillRequest(args[2], generations);
                result = new FillService().fill(request);

                Writer resBody = new OutputStreamWriter(exchange.getResponseBody());
                if (result.isSuccess()) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                } else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                }
                gson.toJson(result, resBody);
                resBody.close();
            }
        } catch (NumberFormatException | JsonIOException | IOException e) {
            System.out.println("Error: " + e.getMessage());
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }
}
