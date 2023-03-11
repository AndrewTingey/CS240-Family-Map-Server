package Handlers;

import Results.Result;
import Services.EventIDService;
import Services.EventService;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;

public class EventIDHandler implements HttpHandler {

    //2 cases
    //  /event
    //  /event/[eventid]
    @Override
    public void handle( HttpExchange exchange ) throws IOException {
        System.out.println("EventID Handler called");
        String URI = exchange.getRequestURI().toString();
        String[] eventsList = URI.split("/");

        try {
            if (exchange.getRequestMethod().equalsIgnoreCase("get")) {
                Headers reqHeaders = exchange.getRequestHeaders();
                String authToken = "";
                if (reqHeaders.containsKey("Authorization")) {
                    authToken = reqHeaders.getFirst("Authorization");
                }
                Gson gson = new Gson();

                Result result = new Result("Error", false);
                if (eventsList.length == 2) { //elements should be "" "events"
                    //events
                    result = new EventService().event(authToken);
                }
                else if (eventsList.length == 3) { //elements should be "" "events" "eventID"
                    //events/[eventID]
                    String eventID = eventsList[2];
                    result = new EventIDService().eventID(eventID, authToken);
                } else {
                    //too many arguments error
                    throw new IOException("incorrect number of arguments");
                }

                Writer resBody = new OutputStreamWriter(exchange.getResponseBody());
                if (result.isSuccess()) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                } else {
                    result = new Result(result.getMessage(), result.isSuccess()); // to eliminate non null values of year, latitude, longitude
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
