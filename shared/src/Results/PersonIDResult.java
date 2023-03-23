package Results;

import Model.Person;

/**
 * Response from searching a person by their ID specifically
 */
public class PersonIDResult extends Result{
    /**
     * Unique identifier for this person
     */
    private String personID;
    /**
     * Username of user to which this person belongs
     */
    private String associatedUsername;
    /**
     * Person's first name
     */
    private String firstName;
    /**
     * Person's last name
     */
    private String lastName;
    /**
     * Person's gender
     */
    private String gender;
    /**
     * Person ID of person’s father
     */
    private String fatherID;
    /**
     * Person ID of person’s mother
     */
    private String motherID;
    /**
     * Person ID of person’s spouse
     */
    private String spouseID;



    /**
     * Default constructor
     */
    public PersonIDResult(String message, boolean success) {
        super(message, success);
    }

    /**
     * parameterized constructor
     */
    public PersonIDResult(  boolean success, Person person ) {
        this.success =  success;
        this.personID = person.getPersonID();
        this.associatedUsername = person.getAssociatedUsername();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.gender = person.getGender();
        this.fatherID = person.getFatherID();
        this.motherID = person.getMotherID();
        this.spouseID = person.getSpouseID();
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID( String personID ) {
        this.personID = personID;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername( String associatedUsername ) {
        this.associatedUsername = associatedUsername;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender( String gender ) {
        this.gender = gender;
    }

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID( String fatherID ) {
        this.fatherID = fatherID;
    }

    public String getMotherID() {
        return motherID;
    }

    public void setMotherID( String motherID ) {
        this.motherID = motherID;
    }

    public String getSpouseID() {
        return spouseID;
    }

    public void setSpouseID( String spouseID ) {
        this.spouseID = spouseID;
    }
}
