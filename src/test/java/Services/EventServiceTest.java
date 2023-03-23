package Services;

import DAO.AuthTokenDAO;
import DAO.DataAccessException;
import DAO.Database;
import DAO.EventDAO;
import Model.Authtoken;
import Model.Event;
import Requests.RegisterRequest;
import Results.ClearResult;
import Results.EventResult;
import Results.RegisterResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventServiceTest {
    private Connection c;
    private Database db;
    Event event1;
    Event event2;

    @BeforeEach
    public void setup() {

        event1 = new Event("ID123", "testing_andrew", "andrew123", 100F, 100F, "Las Vegas", "NV", "Party_Time", 2023);
        event2 = new Event("ID456", "testing_andrew", "andrew123", -10F, -10F, "Provo", "UT", "Study_Time", 2023);
        Authtoken authtoken = new Authtoken("testing_authtoken", "testing_andrew");
        //insert event and authtoken
        try {
            this.db = new Database();
            this.c = db.openConnection();
            new EventDAO(c).insert(event1);
            new EventDAO(c).insert(event2);
            new AuthTokenDAO(c).insert(authtoken);
            db.closeConnection(true);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            db.closeConnection(false);
        }
    }

    @Test
    public void eventPass() {
        EventResult r = new EventService().event("testing_authtoken");
        List<Event> eventListResult = r.getData();
        List<Event> eventListExpected = new ArrayList<>();
        eventListExpected.add(event1);
        eventListExpected.add(event2);

        assertTrue(r.isSuccess());
        assertEquals(eventListExpected, eventListResult);
    }

    @Test
    public void eventFail() {
        EventResult r = new EventService().event("not an authtoken");
        assertFalse(r.isSuccess());
    }

    @AfterEach
    public void takeDown()  {
        //take authtoken and events back out
        try {
            this.db = new Database();
            this.c = db.openConnection();
            new EventDAO(c).clearAll("testing_andrew");
            new AuthTokenDAO(c).clearAll("testing_andrew");
            db.closeConnection(true);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            db.closeConnection(false);
        }
    }
}
