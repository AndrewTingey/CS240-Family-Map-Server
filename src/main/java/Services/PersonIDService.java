package Services;

import DAO.AuthTokenDAO;
import DAO.DataAccessException;
import DAO.Database;
import DAO.PersonDAO;
import Model.Authtoken;
import Model.Person;
import Results.PersonIDResult;

import java.sql.Connection;
import java.util.Objects;

/**
 * Object to search person based off their ID specifically
 */
public class PersonIDService {
    /**
     * Returns the single Person object with the specified ID (if the person is associated with the current user). The current user is determined by the provided authtoken.
     */
    public PersonIDResult personID(String personID, String authtoken) {
        Database db = new Database();
        try {
            Connection c = db.openConnection();

            //get username from authtoken
            Authtoken a = new AuthTokenDAO(c).find(authtoken);
            //authtoken doesn't exits
            if (a == null) {
                throw new DataAccessException("Authtoken does not exist");
            }
            String username = a.getUsername();

            Person personFound = new PersonDAO(c).find(personID);
            if (personFound == null) {
                //no person found
                throw new DataAccessException("Person not found in database");
            }

            //confirm personFound is with authToken
            if(!Objects.equals(username, personFound.getAssociatedUsername())) {
                //event found, but authtoken doesn't match
                throw new DataAccessException("Invalid authtoken");
            }

            db.closeConnection(true);
            PersonIDResult result = new PersonIDResult(true, personFound);//fill up
            return result;
        } catch (DataAccessException e) {
            db.closeConnection(false);
            PersonIDResult result = new PersonIDResult("Error: " + e.getMessage(), false);
            return result;
        }
    }

    /**
     * default constructor
     */
    public PersonIDService() {
    }
}
