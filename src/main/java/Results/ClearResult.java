/**
 * Results represent responses from the database
 */
package Results;

/**
 * Result object from clearing the database
 */
public class ClearResult extends Result {
    /**
     * default constructor
     */
    public ClearResult() {
    }

    /**
     * parameterized constructor
     * @param message
     * @param success
     */
    public ClearResult( String message, boolean success ) {
        this.message = message;
        this.success = success;
    }
}
