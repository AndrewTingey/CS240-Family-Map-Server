package Services;
import DAO.*;
import Model.Authtoken;
import Model.Person;
import Model.User;
import Requests.FillRequest;
import Requests.RegisterRequest;
import Results.FillResult;
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

            //Check username already exists
            if (new UserDAO(c).find(r.getUsername()) != null) {
                throw new DataAccessException("Username \"" + r.getUsername() + "\" already taken");
            }

            String tokenString = new Authtoken().generateAuthtoken();
            String personID = new Person().generatePersonID(r.getUsername());
            Authtoken authtoken = new Authtoken(tokenString, r.getUsername());
            User user = new User(r.getUsername(), r.getPassword(), r.getEmail(), r.getFirstName(), r.getLastName(), r.getGender(), personID);

            //generate 4 generations of ancestor data for new user
            FillRequest fr = new FillRequest(r.getUsername(), 4);
            FillResult fillFromRegister = new FillService().fillFromRegister(fr, c, personID, r.getFirstName(), r.getLastName(), r.getGender()); //this assumes the person registering is not married
            System.out.println(fillFromRegister.getMessage() + " for user " + r.getUsername());


            new UserDAO(c).insert(user);
            new AuthTokenDAO(c).insert(authtoken);

            db.closeConnection(true);

            return new RegisterResult(authtoken.getAuthtoken(), r.getUsername(), personID, true);
        } catch (DataAccessException e) {
            System.out.println("Exception thrown: " + e.getMessage());
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