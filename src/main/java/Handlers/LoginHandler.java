package Handlers;

import Requests.LoginRequest;
import Requests.Request;
import Results.LoginResult;
import Results.Result;
import Services.LoginService;

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