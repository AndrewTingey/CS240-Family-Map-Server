/**
 * Package Requests represent requests from the Handler to be passed to DAOs or Models
 */
package Requests;

import Model.*;

/**
 * Request to load values into the database
 */
public class LoadRequest {
    /**
     * array of user objects to be loaded
     */
    private User[] users;
    /**
     * array of person objects to be loaded
     */
    private Person[] persons;
    /**
     * array of event objects to be laoded
     */
    private Event[] events;

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
    public LoadRequest( User[] users, Person[] persons, Event[] events ) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers( User[] users ) {
        this.users = users;
    }

    public Person[] getPersons() {
        return persons;
    }

    public void setPersons( Person[] persons ) {
        this.persons = persons;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents( Event[] events ) {
        this.events = events;
    }
}
