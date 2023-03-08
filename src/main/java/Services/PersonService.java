package Services;

import DAO.AuthTokenDAO;
import DAO.DataAccessException;
import DAO.Database;
import DAO.PersonDAO;
import Model.Authtoken;
import Model.Person;
import Results.PersonResult;

import java.sql.Connection;
import java.util.List;

/**
 * Object to search person in database
 */
public class PersonService {
    /**
     * Returns ALL family members of the current user. The current user is determined by the provided authtoken.
     */
    public PersonResult person(String authtoken){
        Database db = new Database();
        try{
            Connection c = db.openConnection();

            //get username from authtoken
            Authtoken a = new AuthTokenDAO(c).find(authtoken);
            if (a == null) {
                throw new DataAccessException("Authtoken does not exist");
            }
            String username = a.getUsername();

            List<Person> data = new PersonDAO(c).findAll(username);

            db.closeConnection(true);
            PersonResult result = new PersonResult("Success", true, data );
            return result;
        } catch (DataAccessException e) {
            db.closeConnection(false);
            PersonResult result = new PersonResult("Error: " + e.getMessage(), false);
        }

        return null;
    }

    /**
     * default constructor
     */
    public PersonService() {
    }
}
