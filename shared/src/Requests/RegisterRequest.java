/**
 * Package Requests represent requests from the Handler to be passed to DAOs or Models
 */
package Requests;

/**
 * request to be received from handler and sent to data access and models
 */
public class RegisterRequest extends Request{
    /**
     * password of registered user
     */
    private String password;
    /**
     * email of registered user
     */
    private String email;
    /**
     * first name of registered user
     */
    private String firstName;
    /**
     * last name of registered user
     */
    private String lastName;
    /**
     * gender of registered user
     */
    private String gender;

    /**
     * default constructor
     */
    public RegisterRequest() {
    }

    /**
     * parameterized constructor
     * @param username
     * @param password
     * @param email
     * @param firstName
     * @param lastName
     * @param gender
     */
    public RegisterRequest( String username, String password, String email, String firstName, String lastName, String gender ) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender( String gender ) {
        this.gender = gender;
    }
}
