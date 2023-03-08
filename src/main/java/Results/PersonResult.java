package Results;

import Model.Person;

import java.util.List;

/**
 * Response from searching person
 */
public class PersonResult extends Result {

    /**
     * array of person objects found in database
     */
    private List<Person> data;

    /**
     * default constructor
     */
    public PersonResult( String message, boolean success ) {
        super(message, success);
    }

    /**
     * success contructor
     * @param data
     * @param message
     * @param success
     */
    public PersonResult( String message, boolean success, List<Person> data ) {
        super(message, success);
        this.data = data;
    }

    public List<Person> getData() {
        return data;
    }

    public void setData( List<Person> data ) {
        this.data = data;
    }
}
