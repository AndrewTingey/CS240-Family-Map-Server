package Results;

/**
 * Results from filling the database with data
 */
public class FillResult {
    /**
     * "Successfully added X persons and Y events to the database." or
     * "Error: " with a description of the error"
     */
    private String message;
    /**
     * if the fill was successful or not
     */
    private boolean success;

    /**
     * default constructor
     */
    public FillResult() {
    }

    /**
     * parameterized constructor
     * @param message
     * @param success
     */
    public FillResult( String message, boolean success ) {
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
