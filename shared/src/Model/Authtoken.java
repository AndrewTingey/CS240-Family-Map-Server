/**
 * Model represent events, persons, users, and auth token
 */
package Model;

import java.util.Objects;
import java.util.UUID;

/**
 * authorization token object
 */
public class Authtoken {
    /**
     * Unique authtoken
     */
    private String authtoken;
    /**
     * Username that is associated with the authtoken
     */
    private String username;

    /**
     * Default constructor for authtoken
     */
    public Authtoken() {
    }

    /**
     * parameterized Constructor
     * @param authtoken authtoken
     * @param username username
     */
    public Authtoken(String authtoken, String username) {
        this.authtoken = authtoken;
        this.username = username;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (!(o instanceof Authtoken)) return false;
        Authtoken authtoken1 = (Authtoken) o;
        return Objects.equals(authtoken, authtoken1.authtoken) && Objects.equals(username, authtoken1.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authtoken, username);
    }

    public String generateAuthtoken() {
        return UUID.randomUUID().toString();
    }
}
