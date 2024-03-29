package Results;

import Model.Event;

/**
 * Results from searching an event by its ID specifically
 */
public class EventIDResult extends Result {
    /**
     * associated username with the found event in the database
     */
    private String associatedUsername;
    /**
     * event ID of the event found in the database
     */
    private String eventID;
    /**
     * person ID of the event found in the database
     */
    private String personID;
    /**
     * latitude of the event found in the database
     */
    private float latitude;
    /**
     * longitude of the event found in the database
     */
    private float longitude;
    /**
     * country of the event found in the database
     */
    private String country;
    /**
     * city of the event found in the database
     */
    private String city;
    /**
     * event type of the event found in the database
     */
    private String eventType;
    /**
     * year when the event found in the database
     */
    private int year;

    /**
     * default constructor
     */
    public EventIDResult( String message, boolean success ) {
        super(message, success);
    }

    /**
     * parameterized contructor
     */
    public EventIDResult( String associatedUsername, String eventID, String personID, float latitude, float longitude, String country, String city, String eventType, int year, boolean success, String message ) {
        this.associatedUsername = associatedUsername;
        this.eventID = eventID;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
        this.success = success;
        this.message = message;
    }

    /**
     * constructor with just objects event and result
     * @param event
     * @param success
     * @param message
     */
    public EventIDResult( Event event, boolean success, String message ) {
        this.associatedUsername = event.getAssociatedUsername();
        this.eventID = event.getEventID();
        this.personID = event.getPersonID();
        this.latitude = event.getLatitude();
        this.longitude = event.getLongitude();
        this.country = event.getCountry();
        this.city = event.getCity();
        this.eventType = event.getEventType();
        this.year = event.getYear();
        this.success = success;
        this.message = message;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername( String associatedUsername ) {
        this.associatedUsername = associatedUsername;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID( String eventID ) {
        this.eventID = eventID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID( String personID ) {
        this.personID = personID;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude( float latitude ) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude( float longitude ) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry( String country ) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity( String city ) {
        this.city = city;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType( String eventType ) {
        this.eventType = eventType;
    }

    public int getYear() {
        return year;
    }

    public void setYear( int year ) {
        this.year = year;
    }
}
