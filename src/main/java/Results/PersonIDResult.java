package Results;

import Model.Person;

/**
 * Response from searching a person by their ID specifically
 */
public class PersonIDResult extends Result{
    /**
     * Person found in database
     */
    Person person;

    /**
     * Default constructor
     */
    public PersonIDResult(String message, boolean success) {
        super(message, success);
    }

    /**
     * parameterized constructor
     */
    public PersonIDResult( String message, boolean success, Person person ) {
        super(message, success);
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson( Person person ) {
        this.person = person;
    }
}
