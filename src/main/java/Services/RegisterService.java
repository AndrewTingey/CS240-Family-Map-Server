package Services;
import DAO.*;
import Model.Authtoken;
import Model.Person;
import Model.User;
import Requests.RegisterRequest;
import Results.RegisterResult;
import java.sql.Connection;

/**
 * Obect to register user into database
 */
public class RegisterService {
    /**
     * - Creates a new user account (user row in the database)
     * - Generates 4 generations of ancestor data for the new user (just like the /fill endpoint if
     *   called with a generations value of 4 and this new user’s username as parameters)
     * - Logs the user in
     * - Returns an authtoken
     * @param r registerRequest from handler
     * @return registerResult with values or error message
     */
    public RegisterResult register(RegisterRequest r){
        Database db = new Database();
        try {
            Connection c = db.openConnection();

            String tokenString = new Authtoken().generateAuthtoken();
            String personID = new Person().generatePersonID(r.getUsername());
            Authtoken authtoken = new Authtoken(tokenString, r.getUsername());
            User user = new User(r.getUsername(), r.getPassword(), r.getEmail(), r.getFirstName(), r.getLastName(), r.getGender(), personID);

            //TODO
            //generate 4 generations of ancestor data for new user
            new UserDAO(c).insert(user);
            new AuthTokenDAO(c).insert(authtoken);

            db.closeConnection(true);

            return new RegisterResult(authtoken.getAuthtoken(), r.getUsername(), personID, true, "Success RS.38"); // no message needed in success case
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            db.closeConnection(false);
            RegisterResult result = new RegisterResult("Error: " + e.getMessage(), false);
            return result;
        }
    }

    /**
     * default constructor
     */
    public RegisterService() {
    }
}