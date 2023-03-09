package Services;

import DAO.*;
import Requests.LoadRequest;
import Results.LoadResult;
import Results.Result;
import Model.*;

import java.sql.Connection;
import java.util.List;

/**
 * object to load specific data into the database
 */
public class LoadService {
    /**
     * loads requested data into the database
     * @param r LoadRequest with data
     * @return LoadResult with success or error message
     */
    public LoadResult load( LoadRequest r ) {
        Database db = new Database();
        try {
            Connection c = db.openConnection();

            List<User> usersToAdd = r.getUsers();
            List<Person> personsToAdd = r.getPersons();
            List<Event> eventsToAdd = r.getEvents();

            for (User user : usersToAdd) {
                new UserDAO(c).insert(user);
            }

            for (Person person : personsToAdd) {
                new PersonDAO(c).insert(person);
            }

            for (Event event : eventsToAdd) {
                new EventDAO(c).insert(event);
            }

            db.closeConnection(true);
            String message = "Successfully added " + usersToAdd.size() +
                    " users, " + personsToAdd.size() + " persons, and " + eventsToAdd.size() + " events to the database";
            System.out.println(message);
            LoadResult result = new LoadResult(message, true);
            return result;
        } catch (DataAccessException e) {
            db.closeConnection(false);
            LoadResult result = new LoadResult("Error: " + e.getMessage(), false);
            return result;
        }
    }

    /**
     * default constructor
     */
    public LoadService() {
    }
}
