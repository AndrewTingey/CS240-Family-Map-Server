package Results;

/**
 * response from loading information into the database
 */
public class LoadResult extends Result {
    /**
     * "message":"Successfully added X users, Y persons, and Z events to the database"
     * or
     * "Error: " with a description of the error
     */
    public LoadResult(String message, boolean success) {
        super(message, success);
    }
}
