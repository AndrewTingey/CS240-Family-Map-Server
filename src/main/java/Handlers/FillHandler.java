package Handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class FillHandler implements HttpHandler {
    @Override
    public void handle( HttpExchange exchange ) throws IOException {
        //2 cases here:
        //  /fill/[username]/{generations}
        //  /fill/[username]
        String urlPath = exchange.getRequestURI().toString();
        //take off "/fill"
        urlPath = urlPath.substring(5);
        String username;
        String generations = "";
        if (urlPath.contains("/")) {
            username = urlPath.substring(0, urlPath.indexOf("/"));
            generations =  urlPath.substring(urlPath.indexOf("/"));
        } else {
            username = urlPath;
        }
        System.out.println("Username = " + username);
        System.out.println("Generations = " + generations);
    }
}
