package Handlers;

import Requests.Request;
import Results.ClearResult;
import Results.Result;
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

public class ClearHandler extends Handler {
    public ClearHandler(  ) {
        super("post", false, false, null);
    }

    @Override
    protected Result doService( Request request, String authToken ) {
        System.out.println("Clear Handler Called");
        return new ClearService().clear();
    }
}
