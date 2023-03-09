package Results;
/**
 * response from registering person into database
 */
public class RegisterResult extends Result{
    /**
     * authorization token from registering
     */
    private String authtoken;
    /**
     * username of successful register
     */
    private String username;
    /**
     * personID of successful register
     */
    private String personID;

    /**
     * default constructor
     */
    public RegisterResult( String message, boolean success ) {
        super(message, success);
    }

    /**
     * parameterized constructor
     * @param authtoken
     * @param username
     * @param personID
     * @param success
     */
    public RegisterResult( String authtoken, String username, String personID, boolean success ) {
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
