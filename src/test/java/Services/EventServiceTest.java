package Services;

import Model.Event;
import Requests.RegisterRequest;
import Results.ClearResult;
import Results.EventResult;
import Results.RegisterResult;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventServiceTest {

    @Test
    public void clearit() {
        //my tests are poorly written and interdependent
        ClearService clearService = new ClearService();
        ClearResult result = clearService.clear();
        assertTrue(result.isSuccess());
    }

    @Test
    public void EventService() { //This doesn't print anything cus theres no events associated with andrewtingey
        RegisterRequest rreq = new RegisterRequest("andrewtingey", "12345", "at@gmail", "Andy", "Ting", "M");
        RegisterResult rr = new RegisterService().register(rreq);
        String authToken = rr.getAuthtoken();

        EventResult r = new EventService().event(authToken);
        List<Event> events = r.getData();
        for (Event event : events) {
            System.out.println(event);
        }
        System.out.println(r.getMessage());
        assertTrue(r.isSuccess());
    }
}
