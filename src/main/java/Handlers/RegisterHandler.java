package Handlers;


import Requests.RegisterRequest;
import Requests.Request;
import Results.RegisterResult;
import Results.Result;
import Services.RegisterService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;


//this does not work!!

public class RegisterHandler extends Handler {

    public RegisterHandler( ) {
        super("post", false, true, new RegisterRequest());
    }

    @Override
    protected Result doService( Request request, String authToken ) {
        System.out.println("Register Handler Called");
        return new RegisterService().register((RegisterRequest) request);
    }
}