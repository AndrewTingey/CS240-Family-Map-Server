package Services;

import Results.EventIDResult;
import Results.EventResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventIDServiceTest {
    private EventIDService evIDService;

    @Test
    public void getIDPass() {
        //EventIDService.java line 23 has critical error !!!
        evIDService = new EventIDService();
        EventIDResult result = evIDService.eventID("an event");
        assertTrue(result.isSuccess());
    }
}
