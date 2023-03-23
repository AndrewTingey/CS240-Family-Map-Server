package Services;

import DAO.*;
import Requests.RegisterRequest;
import Results.EventIDResult;
import Results.PersonIDResult;
import Results.RegisterResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class PersonIDServiceTest {
    private String personID;
    private String at;
    private String at2;
    @BeforeEach
    public void setup() {
        //register self
        RegisterRequest request = new RegisterRequest("testing_andrew", "12345", "at@gmail", "Andy", "Ting", "M");
        RegisterResult r1 = new RegisterService().register(request);
        at = r1.getAuthtoken();

        //register other guy
        request = new RegisterRequest("testing_other", "67890", "gt@gmail", "gran", "Ting", "M");
        RegisterResult r2 = new RegisterService().register(request);
        at2 = r2.getAuthtoken();

        personID = r1.getPersonID();
    }
    @Test
    public void personIDPass() {
        PersonIDService personIDService = new PersonIDService();
        PersonIDResult result = personIDService.personID(personID,at);
        assertTrue(result.isSuccess());
    }

    @Test
    public void personIDfail() {
        //invalid personID, valid authtoken
        PersonIDResult result = new PersonIDService().personID("invalidPersonID",at);
        assertFalse(result.isSuccess());

        //valid personID, invalid authtoken
        result = new PersonIDService().personID(personID,"invalidAuthtoken");
        assertFalse(result.isSuccess());

        //valid personID and authtoken, but different users
        result = new PersonIDService().personID(personID,at2);
        assertFalse(result.isSuccess());
    }

    @AfterEach
    public void takeDown() {
        Database db = new Database();
        try {
            Connection c = db.openConnection();
            new EventDAO(c).clearAll("testing_andrew");
            new AuthTokenDAO(c).clearAll("testing_andrew");
            new PersonDAO(c).clearAll("testing_andrew");
            new UserDAO(c).clearAll("testing_andrew");
            new EventDAO(c).clearAll("testing_other");
            new AuthTokenDAO(c).clearAll("testing_other");
            new PersonDAO(c).clearAll("testing_other");
            new UserDAO(c).clearAll("testing_other");
            db.closeConnection(true);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            db.closeConnection(false);
        }
    }
}
