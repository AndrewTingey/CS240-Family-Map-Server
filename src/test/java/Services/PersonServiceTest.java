package Services;

import DAO.*;
import Model.Authtoken;
import Model.Event;
import Model.Person;
import Requests.RegisterRequest;
import Results.PersonResult;
import Results.RegisterResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PersonServiceTest {private Connection c;
    private Database db;
    Person person1;
    Person person2;

    @BeforeEach
    public void setup() {

        person1 = new Person("testingID", "testing_andrew", "testing_andrew", "testing_tingey", "M", null, null, null);
        person2 = new Person("otherID", "testing_andrew", "other_guy", "testing_tingey", "M", null, null, null);
        Authtoken authtoken = new Authtoken("testing_authtoken", "testing_andrew");
        //insert persons and authtoken
        try {
            this.db = new Database();
            this.c = db.openConnection();
            new PersonDAO(c).insert(person1);
            new PersonDAO(c).insert(person2);
            new AuthTokenDAO(c).insert(authtoken);
            db.closeConnection(true);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            db.closeConnection(false);
        }
    }

    @Test
    public void personPass() {
        PersonResult r = new PersonService().person("testing_authtoken");
        List<Person> personListResult = r.getData();
        List<Person> personListExpected = new ArrayList<>();
        personListExpected.add(person1);
        personListExpected.add(person2);

        assertTrue(r.isSuccess());
        assertEquals(personListExpected, personListResult);
    }
    @Test
    public void personFail() {
        PersonResult r = new PersonService().person("notanauthtoken");
        assertFalse(r.isSuccess());
    }

    @AfterEach
    public void takeDown()  {
        //take authtoken and persons back out
        try {
            this.db = new Database();
            this.c = db.openConnection();
            new PersonDAO(c).clearAll("testing_andrew");
            new AuthTokenDAO(c).clearAll("testing_andrew");
            db.closeConnection(true);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            db.closeConnection(false);
        }
    }


}
