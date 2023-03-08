package Results;

/**
 * "Successfully added X persons and Y events to the database." or
 * "Error: " with a description of the error"
 */
/**
 * Results from filling the database with data
 */
public class FillResult extends Result{

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
        super(message, success);
    }
}
