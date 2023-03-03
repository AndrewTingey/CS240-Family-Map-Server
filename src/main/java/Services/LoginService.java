package Services;

import DAO.AuthTokenDAO;
import DAO.DataAccessException;
import DAO.Database;
import DAO.UserDAO;
import Model.Authtoken;
import Model.User;
import Requests.LoginRequest;
import Results.LoginResult;

import java.sql.Connection;
import java.util.Objects;

/**
 * object to log in user
 */
public class LoginService {
    /**
     * Log in user with LoginRequest
     * @param r login request
     * @return Login result where success = true with values or success = false with error message
     */
    public LoginResult login( LoginRequest r ) {
        Database db = new Database();
        try {
            Connection c = db.openConnection();

            User user = new UserDAO(c).find(r.getUsername());
            if (!Objects.equals(r.getPassword(), user.getPassword())) {
                throw new BadPasswordException("Incorrect Password");
            }

            Authtoken authtoken = new AuthTokenDAO(c).findAuthtoken(r.getUsername());

            db.closeConnection(true);
            return new LoginResult(authtoken.getAuthtoken(), user.getUsername(), user.getPersonID(), true, "Success");
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            db.closeConnection(false);
            return new LoginResult("Error: " + e.getMessage(), false);
        } catch (BadPasswordException e) {
            db.closeConnection(false);
            return new LoginResult("Error: " + e.getMessage(), false);
        }
    }

    /**
     * default constructor
     */
    public LoginService() {
    }
}
