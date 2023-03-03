package Results;

/**
 * response from loading information into the database
 */
public class LoadResult {
    /**
     * "message":"Successfully added X users, Y persons, and Z events to the database"
     * or
     * "Error: " with a description of the error
     */
    private String message;
    /**
     * If loading data into the database was successful or not
     */
    private boolean success;

    /**
     * default constructor
     */
    public LoadResult() {
    }

    /**
     * parameterized constructor
     * @param message
     * @param success
     */
    public LoadResult( String message, boolean success ) {
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
