package Services;

import Requests.LoginRequest;
import Results.LoginResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {
    private LoginResult loginResult;

    @Test
    public void login() {
        LoginRequest request = new LoginRequest("andrewtingey", "12345");
        loginResult = new LoginService().login(request);
        System.out.println(loginResult.getMessage());
        assertTrue(loginResult.isSuccess());
        assertEquals("304874a5-0330-4586-bf1d-d13a8ceb7d0d", loginResult.getAuthtoken());
    }
}
