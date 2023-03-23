package Handlers;

import Requests.Request;
import Results.ClearResult;
import Results.Result;
import Services.ClearService;

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
