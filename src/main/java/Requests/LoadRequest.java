/**
 * Package Requests represent requests from the Handler to be passed to DAOs or Models
 */
package Requests;

import Model.*;

import java.util.List;

/**
 * Request to load values into the database
 */
public class LoadRequest extends Request{
    /**
     * array of user objects to be loaded
     */
    private List<User> users;
    /**
     * array of person objects to be loaded
     */
    private List<Person> persons;
    /**
     * array of event objects to be laoded
     */
    private List<Event> events;

    /**
     * default constructor
     */
    public LoadRequest() {
    }

    /**
     * parameterized constructor for all values
     * @param users
     * @param persons
     * @param events
     */
    public LoadRequest( List<User> users, List<Person> persons, List<Event> events ) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }
    public List<User> getUsers() {
        return users;
    }

    public void setUsers( List<User> users ) {
        this.users = users;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons( List<Person> persons ) {
        this.persons = persons;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents( List<Event> events ) {
        this.events = events;
    }
}
