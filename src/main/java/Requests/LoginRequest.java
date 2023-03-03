/**
 * Package Requests represent requests from the Handler to be passed to DAOs or Models
 */
package Requests;

import Results.LoginResult;

/**
 * request to log in user
 */
public class LoginRequest {
    /**
     * username entered at login
     */
    private String username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }
}
