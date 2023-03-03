package Services;

import DAO.*;
import Model.Event;
import Results.ClearResult;
import Results.EventIDResult;

import java.sql.Connection;

/**
 * Object to search an event off of its ID specifically
 */
public class EventIDService {
    /**
     * Returns the single Event object with the specified ID
     * (if the event is associated with the current user). The
     * current user is determined by the provided authtoken.
     *
     */
    public EventIDResult eventID(String eventID) { //or should be authtoken or currentUser?
        Database db = new Database();
        try {
            Connection c = db.openConnection();
            Event efound = new EventDAO(c).find(eventID);

            //TODO
            //do more

            db.closeConnection(true);
            return new EventIDResult(efound, true, "Event found.");
        } catch (DataAccessException e) {
            db.closeConnection(false);
            EventIDResult result = new EventIDResult("Error: " + e.getMessage(), false);
            return result;
        }
    }

    /**
     * default constructor
     */
    public EventIDService() {
    }
}
