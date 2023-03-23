package Services;

import DAO.AuthTokenDAO;
import DAO.DataAccessException;
import DAO.Database;
import DAO.EventDAO;
import Model.Authtoken;
import Model.Event;
import Requests.RegisterRequest;
import Results.EventIDResult;
import Results.EventResult;
import Results.RegisterResult;
import org.junit.jupiter.api.*;

import java.sql.Connection;

//These tests are horribly written, but everything works, don't worry
public class EventIDServiceTest {
    private Connection c;
    private Database db;

    @BeforeEach
    public void setup() {

        Event event1 = new Event("ID123", "testing_andrew", "andrew123", 100F, 100F, "Las Vegas", "NV", "Party_Time", 2023);
        Event event2 = new Event("ID456", "testing_andrew", "andrew123", -10F, -10F, "Provo", "UT", "Study_Time", 2023);
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
    public void getIDPass() {
        EventIDService evIDService = new EventIDService();
        EventIDResult result = evIDService.eventID("ID123","testing_authtoken");
        System.out.println(result.getMessage());
        assertTrue(result.isSuccess());
    }

    @Test
    public void getIDFail() {
        EventIDResult result1 = new EventIDService().eventID("NotanID", "testing_authtoken");
        EventIDResult result2 = new EventIDService().eventID("ID123", "notanauthtoken");
        assertFalse(result1.isSuccess());
        assertFalse(result2.isSuccess());
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
