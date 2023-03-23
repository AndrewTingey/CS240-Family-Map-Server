package Handlers;


import Requests.RegisterRequest;
import Requests.Request;
import Results.RegisterResult;
import Results.Result;
import Services.RegisterService;


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