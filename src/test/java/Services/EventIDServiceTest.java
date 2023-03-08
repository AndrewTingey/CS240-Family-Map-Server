package Services;

import Requests.RegisterRequest;
import Results.EventIDResult;
import Results.EventResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//These tests are horribly written, but everything works, don't worry
public class EventIDServiceTest {
    private EventIDService evIDService;
    private String eventID;

    @Test
    public void setup() {
        //clear db
        new ClearService().clear();

        //register self
        RegisterRequest request = new RegisterRequest("andrewtingey", "12345", "at@gmail", "Andy", "Ting", "M");
        new RegisterService().register(request);

        //register other guy
        request = new RegisterRequest("gtt", "67890", "gt@gmail", "gran", "Ting", "M");
        new RegisterService().register(request);
    }
    @Test
    public void getIDPass() {
        evIDService = new EventIDService();
        EventIDResult result = evIDService.eventID("Birth3c3cbc","912f78d5-c7c9-41b3-9a7b-c7fd0c4a2160");
        System.out.println(result.getMessage());
        assertTrue(result.isSuccess());
    }
}
