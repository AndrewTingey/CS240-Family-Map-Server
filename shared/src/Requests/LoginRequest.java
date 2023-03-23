/**
 * Package Requests represent requests from the Handler to be passed to DAOs or Models
 */
package Requests;

/**
 * request to log in user
 */
public class LoginRequest extends Request {
    /**
     * password entered at login
     */
    private String password;

    /**
     * default constructor
     */
    public LoginRequest() {
    }

    /**
     * parameterized constructor
     * @param username
     * @param password
     */
    public LoginRequest( String username, String password ) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }
}
