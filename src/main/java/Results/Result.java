
package Results;

/**
 * Result object from clearing the database
 */
public class Result {
    /**
     * Success message:
     * "Clear succeeded." or
     * "Error: " with a description of the error
     */
    protected String message;
    /**
     * If result of action was successful or not
     */
    protected boolean success;

    /**
     * default constructor
     */
    public Result() {
    }

    /**
     * parameterized constructor
     * @param message
     * @param success
     */
    public Result ( String message, boolean success ) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage( String message ) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess( boolean success ) {
        this.success = success;
    }
}
