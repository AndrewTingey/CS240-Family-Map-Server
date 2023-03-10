package Handlers;

import Requests.Request;
import Results.Result;
import Services.EventService;

//this file is overridden by EventIDHandler
// to determine between /event and /event/eventID
public class EventHandler extends Handler{
    public EventHandler( ) {
        super("get", true, false, null);
    }

    @Override
    protected Result doService( Request request, String authToken ) {
        return new EventService().event(authToken);
    }
}
