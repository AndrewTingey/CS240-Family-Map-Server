package Services;

import DAO.*;
import Model.Authtoken;
import Model.Event;
import Results.ClearResult;
import Results.EventIDResult;
import Results.Result;

import java.sql.Connection;
import java.util.Objects;

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
    public EventIDResult eventID(String eventID, String authtoken) { //or should be authtoken or currentUser?
        Database db = new Database();
        try {
            Connection c = db.openConnection();

            //get username from authtoken
            Authtoken a = new AuthTokenDAO(c).find(authtoken);
            //authtoken doesnt even exist
            if (a == null) {
                throw new DataAccessException("Authtoken does not exist");
            }
            String username = a.getUsername();

            Event eventFound = new EventDAO(c).find(eventID);
            if (eventFound == null) {
                //event was not found
                throw new DataAccessException("Event not found");
            }

            //confirm eventFound is by the same user
            if (!Objects.equals(username, eventFound.getAssociatedUsername())) {
                //event was found, but authtoken didn't match
                throw new DataAccessException("Invalid authtoken");
            }

            db.closeConnection(true);
            return new EventIDResult(eventFound, true, "Event found.");
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
