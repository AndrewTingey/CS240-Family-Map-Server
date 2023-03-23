package Services;

import DAO.*;
import Requests.RegisterRequest;
import Results.ClearResult;
import Results.RegisterResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class RegisterServiceTest {
    private RegisterResult registerResult;

    @Test
    public void registerPass() {
        RegisterRequest request = new RegisterRequest("testing_andrew", "12345", "at@gmail", "Andy", "Ting", "M");
        registerResult = new RegisterService().register(request);
        assertTrue(registerResult.isSuccess());
    }
    @Test
    public void registerFail() {
        RegisterRequest request = new RegisterRequest("testing_andrew", "12345", "at@gmail", "Andy", "Ting", "M");
        registerResult = new RegisterService().register(request);
        //username already taken
        registerResult = new RegisterService().register(request);
        assertFalse(registerResult.isSuccess());
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
