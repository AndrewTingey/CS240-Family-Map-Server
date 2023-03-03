package Results;

import Model.Person;

/**
 * Response from searching person
 */
public class PersonResult {

    /**
     * array of person objects found in database
     */
    private Person[] data;
    /**
     * error message if unsuccessful
     */
    private String message;
    /**
     * if person was found in database or not
     */
    private boolean success;

    /**
     * default constructor
     */
    public PersonResult() {
    }

    /**
     * parameterized contructor
     * @param data
     * @param message
     * @param success
     */
    public PersonResult( Person[] data, String message, boolean success ) {
        this.data = data;
        this.message = message;
        this.success = success;
    }

    public Person[] getData() {
        return data;
    }

    public void setData( Person[] data ) {
        this.data = data;
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
