package Services;

import DAO.DataAccessException;
import Requests.RegisterRequest;
import Results.EventIDResult;
import Results.PersonIDResult;
import Results.RegisterResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonIDServiceTest {

    private String personID;
    private String at;
    private String at2;
    @BeforeEach
    public void setup() {
        //clear db
        new ClearService().clear();

        //register self
        RegisterRequest request = new RegisterRequest("andrewtingey", "12345", "at@gmail", "Andy", "Ting", "M");
        RegisterResult r1 = new RegisterService().register(request);
        at = r1.getAuthtoken();

        //register other guy
        request = new RegisterRequest("gtt", "67890", "gt@gmail", "gran", "Ting", "M");
        RegisterResult r2 = new RegisterService().register(request);
        at2 = r2.getAuthtoken();

        personID = r1.getPersonID();
    }
    @Test
    public void getIDPass() {
        PersonIDService personIDService = new PersonIDService();
        PersonIDResult result = personIDService.personID(personID,at);
        System.out.println(result.getMessage());
        System.out.println(at);
        assertTrue(result.isSuccess());

        //invalid personID, valid authtoken
        result = personIDService.personID("invalidPersonID",at);
        System.out.println(result.getMessage());
        assertFalse(result.isSuccess());

        //valid personID, invalid authtoken
        result = personIDService.personID(personID,"invalidAuthtoken");
        System.out.println(result.getMessage());
        assertFalse(result.isSuccess());

        //valid personID and authtoken, but different users
        result = personIDService.personID(personID,at2);
        System.out.println(result.getMessage());
        assertFalse(result.isSuccess());
    }
}
