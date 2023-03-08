package Services;

import Model.Person;
import Requests.RegisterRequest;
import Results.PersonResult;
import Results.RegisterResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonServiceTest {
    @BeforeEach
    public void clear() {
        new ClearService().clear();
    }

    @Test
    public void PersonService() {
        RegisterRequest rreq = new RegisterRequest("andrewtingey", "12345", "at@gmail", "Andy", "Ting", "M");
        RegisterResult rr = new RegisterService().register(rreq);
        String authToken = rr.getAuthtoken();

        PersonResult r = new PersonService().person(authToken);
        List< Person> persons = r.getData();
        for (Person person: persons) {
            System.out.println(person.getFirstName());
        }
        System.out.println(r.getMessage());
        assertTrue(r.isSuccess());
    }
}
