package Services;

import Model.*;
import Requests.LoadRequest;
import Results.LoadResult;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoadServiceTest {

    @Test
    public void clear() {
        new ClearService().clear();
    }

    @Test
    public void loadData() {
        List<User> users = new ArrayList<>();
        List<Person> persons = new ArrayList<>();
        List<Event> events = new ArrayList<>();

        User a = new User("user1", "pass1", "1@mail.com", "albert", "zeta", "M", "123");
        User b = new User("user2", "pass2", "2@mail.com", "beta", "yankee", "M", "456");
        User c = new User("user3", "pass3", "3@mail.com", "charli", "xray", "M", "789");
        users.add(a); users.add(b); users.add(c);

        Person d = new Person("246", "user1", "don", "zeta", "F", null, null, null );
        Person e = new Person("135", "user1", "edward", "zeta", "F", null, null, null );
        Person f = new Person("802", "user1", "fatima", "zeta", "F", null, null, null );
        persons.add(d); persons.add(e); persons.add(f);

        Event h = new Event("Event1", "user1", "246", 10F, 40F, "USA", "Provo", "Creation", 2023);
        events.add(h);

        LoadRequest request = new LoadRequest(users, persons, events);
        LoadResult result = new LoadService().load(request);

        System.out.println(result.getMessage());
        assertTrue(result.isSuccess());
    }
}
