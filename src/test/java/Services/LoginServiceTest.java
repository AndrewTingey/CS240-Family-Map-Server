package Services;

import DAO.*;
import Requests.LoginRequest;
import Requests.RegisterRequest;
import Results.LoginResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class LoginServiceTest {
    @BeforeEach
    public void setUp() {
        RegisterRequest r = new RegisterRequest("testing_andrew", "pw123", "at@gmail", "Testing_Andrew", "Testing_Tingey", "M");
        new RegisterService().register(r);
    }

    @Test
    public void loginPass() {
        LoginRequest request = new LoginRequest("testing_andrew", "pw123");
        LoginResult loginResult = new LoginService().login(request);
        System.out.println(loginResult.getMessage());
        assertTrue(loginResult.isSuccess());
    }
    @Test
    public void loginFail() {
        LoginRequest request = new LoginRequest("not a username", "pw123");
        LoginResult loginResult = new LoginService().login(request);
        assertFalse(loginResult.isSuccess());

        request = new LoginRequest("testing_anderew", "not correct password");
        loginResult = new LoginService().login(request);
        assertFalse(loginResult.isSuccess());
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
