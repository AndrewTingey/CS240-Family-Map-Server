package Handlers;

import Requests.LoadRequest;
import Requests.Request;
import Results.Result;
import Services.LoadService;

public class LoadHandler extends Handler {
    public LoadHandler( ) {
        super("post", false, true, new LoadRequest());
    }

    @Override
    protected Result doService( Request request, String authToken ) {
        System.out.println("Load Handler Called");
        return new LoadService().load((LoadRequest) request);
    }
}
