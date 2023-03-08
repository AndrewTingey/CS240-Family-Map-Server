package Services;

import DAO.AuthTokenDAO;
import DAO.DataAccessException;
import DAO.Database;
import DAO.EventDAO;
import Model.Authtoken;
import Model.Event;
import Results.EventResult;

import java.sql.Connection;
import java.util.List;

/**
 * object to search event in the database
 */
public class EventService {
    /**
     * Returns ALL events for ALL family members of the current user. The current user is determined from the provided auth token
     */
    public EventResult event(String authToken) {
        Database db = new Database();
        try {
            Connection c = db.openConnection();

            //get username from authtoken
            Authtoken a = new AuthTokenDAO(c).find(authToken);
            if (a == null) {
                throw new DataAccessException("Authtoken does not exist");
            }
            String username = a.getUsername();

            //get all events for all family members of the current user
            List<Event> data = new EventDAO(c).findAll(username);

            db.closeConnection(true);
            EventResult result = new EventResult(data, true, "A message in ES.22");
            return result;
        } catch (DataAccessException e) {
            db.closeConnection(false);
            EventResult result = new EventResult(null, false, "Error: " + e.getMessage());
            return result;
        }
    }

    /**
     * default constructor
     */
    public EventService() {

    }
}
