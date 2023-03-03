/**
 * Services are recieved from the Handlers to be passed on to data access objects via Requests and Results
 */
package Services;

import DAO.*;
import Results.ClearResult;

import java.sql.Connection;

/**
 * To clear the entire database
 */
public class ClearService {
    /*
    WHAT DO SERVICE CLASSES DO?
    FMS implementation.pptx slide 5
     */
    /**
     * Clears everything from the database
     * @return clearresult with success or error message
     */
    public ClearResult clear(){
        Database db = new Database();
        try {
            Connection c = db.openConnection();
            new AuthTokenDAO(c).clear();
            new EventDAO(c).clear();
            new PersonDAO(c).clear();
            new UserDAO(c).clear();

            db.closeConnection(true);
            return new ClearResult("Clear succeeded.", true);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            db.closeConnection(false);
            ClearResult result = new ClearResult("Error: " + e.getMessage(), false);
            return result;
        }
    }

    /**
     * default constructor
     */
    public ClearService() {
    }
}
