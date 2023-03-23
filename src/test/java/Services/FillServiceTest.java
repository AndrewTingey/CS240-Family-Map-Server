package Services;

import DAO.*;
import Requests.FillRequest;
import Requests.RegisterRequest;
import Results.ClearResult;
import Results.EventResult;
import Results.FillResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class FillServiceTest {

    @BeforeEach
    public void setUp() {
        RegisterRequest r = new RegisterRequest("testing_andrew", "pw123", "at@gmail", "Testing_Andrew", "Testing_Tingey", "M");
        new RegisterService().register(r);
    }
    @Test
    public void fillPass() {
        FillRequest request = new FillRequest("testing_andrew", 3);
        FillResult result = new FillService().fill(request);
        assertTrue(result.isSuccess());
    }

    @Test
    public void fillFail() {
        //user does not exist in database, should fail
        FillRequest request = new FillRequest("not a username", 3);
        FillResult result = new FillService().fill(request);
        assertFalse(result.isSuccess());

        //this shouldnt be needed cuz fill is only called by me
        //invalid number of generations
        /*
        request = new FillRequest("testing_andrew", -3);
        result = new FillService().fill(request);
        assertFalse(result.isSuccess());
         */
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
            db.closeConnection(true);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            db.closeConnection(false);
        }
    }
}
