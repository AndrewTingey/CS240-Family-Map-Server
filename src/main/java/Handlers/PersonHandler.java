package Handlers;

import Requests.Request;
import Results.Result;
import Services.PersonIDService;
import Services.PersonService;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;

//2 cases here:
//      /person
//      /person/[personID]
public class PersonHandler implements HttpHandler {
    @Override
    public void handle( HttpExchange exchange ) throws IOException {
        System.out.println("Person Handler called");
        String[] args = exchange.getRequestURI().toString().split("/");
        try {
            if (exchange.getRequestMethod().equalsIgnoreCase("get")) {
                Headers reqHeaders = exchange.getRequestHeaders();
                String authtoken = "";
                if (reqHeaders.containsKey("Authorization")) {
                    authtoken = reqHeaders.getFirst("Authorization");
                }
                Gson gson = new Gson();
                Result result = new Result("Error: PH.33", false);
                String personID;
                if (args.length == 2) { // "" / "person" / "personID"
                    result = new PersonService().person(authtoken);
                } else if (args.length == 3) {
                    personID = args[2];
                    result = new PersonIDService().personID(personID, authtoken);
                } else {
                    throw new IOException("Invalid number of arguments");
                }

                Writer resBody = new OutputStreamWriter(exchange.getResponseBody());
                if (result.isSuccess()) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                } else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                }
                gson.toJson(result, resBody);
                resBody.close();
            }
        } catch (IOException | JsonIOException e) {
            System.out.println("Error: " + e.getMessage());
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }
}
