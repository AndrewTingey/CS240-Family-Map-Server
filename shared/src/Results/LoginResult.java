package Results;

/**
 * Object for the response from logging in
 */
public class LoginResult extends Result {
    /**
     * authorization token from logging in
     */
    private String authtoken;
    /**
     * username of successful login
     */
    private String username;
    /**
     * personID of successful login
     */
    private String personID;

    /**
     * default constructor
     */
    public LoginResult( String message, boolean success ) {
        super(message, success);
    }

    /**
     * parameterized constructor
     * @param authtoken
     * @param username
     * @param personID
     * @param success
     */
    public LoginResult( String authtoken, String username, String personID, boolean success ) {
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
        this.success = success;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken( String authtoken ) {
        this.authtoken = authtoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID( String personID ) {
        this.personID = personID;
    }
}
