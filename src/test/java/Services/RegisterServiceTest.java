package Services;

import Requests.RegisterRequest;
import Results.ClearResult;
import Results.RegisterResult;
import Services.RegisterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterServiceTest {
    private RegisterResult registerResult;


    @Test
    public void register() {
        RegisterRequest request = new RegisterRequest("andrewtingey", "12345", "at@gmail", "Andy", "Ting", "M");
        registerResult = new RegisterService().register(request);
        assertTrue(registerResult.isSuccess());
        System.out.println(registerResult.getMessage());
    }
}
