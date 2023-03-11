package Handlers;

import Requests.LoginRequest;
import Requests.Request;
import Results.LoginResult;
import Results.Result;
import Services.LoginService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;

public class LoginHandler extends Handler {

    public LoginHandler() {
        super("post", false, true, new LoginRequest());
    }

    @Override
    protected Result doService( Request request, String authToken ) {
        System.out.println("Login handler called");
        return new LoginService().login((LoginRequest) request);
    }
}