package Services;

import DAO.*;
import Model.Authtoken;
import Model.Event;
import Model.Location;
import Model.Person;
import ObjectDecoder.FNames;
import ObjectDecoder.LocationDeserializer;
import ObjectDecoder.MNames;
import ObjectDecoder.SNames;
import Requests.FillRequest;
import Results.FillResult;

import java.sql.Connection;
import java.util.Random;

/**
 * object to fill the database with data
 */
public class FillService {
    private String associatedUsername;
    private int personsAdded = 0;
    private int eventsAdded = 0;
    private FNames fNames = new FNames();
    private MNames mnames = new MNames();
    private SNames snames = new SNames();
    private LocationDeserializer locations = new LocationDeserializer();
    /**
     * fills the data base with info
     * @return result with success or error message
     *
     */
    public FillResult fill( FillRequest r ) {

        this.associatedUsername = r.getUsername();
        int generations = r.getGenerations();

        Database db = new Database();
        try {
            Connection c = db.openConnection();

            //check if username is in database
            Authtoken a = new AuthTokenDAO(c).findAuthtoken(associatedUsername);
            if (a == null) {
                throw new DataAccessException("Username \"" + associatedUsername + "\" does not exist");
            }

            String personID = new Person().generatePersonID(associatedUsername);
            generatePerson("M", generations, c, 2001, personID, null);

            db.closeConnection(true);

            String message = "Successfully added " + personsAdded + " persons and " + eventsAdded + " events to the database.";
            return new FillResult(message, true);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            db.closeConnection(false);
            FillResult result = new FillResult("Error: " + e.getMessage(), false);
            return result;
        }
    }
    public FillResult fillFromRegister ( FillRequest r, Connection c, String personID, String firstName, String lastName, String gender) {
        this.associatedUsername = r.getUsername();
        int generations = 4; //default for registering

        Database db = new Database();
        try {
            //dont open or close connection bc registerService already has one passed as c
            //do first generation with firstname/lastname, then pass on to generatePerson()

            //generate events for person here
            //int childsBday = getkidsBday();
            Event birthDate = generateBirthdate(2001);
            Event deathDate = generateDeathDate(birthDate);
            birthDate.setPersonID(personID);
            deathDate.setPersonID(personID);

            String momID = new Person().generatePersonID("Mom");
            String dadID = new Person().generatePersonID("Dad");

            Person mother = generatePerson("F", generations - 1, c, birthDate.getYear(), momID, dadID);
            Person father = generatePerson("M", generations - 1, c, birthDate.getYear(), dadID, momID);

            //add marriage event to both mother and father
            Event marriageDate = generateMarriage(birthDate.getYear());
            marriageDate.setPersonID(mother.getPersonID());
            new EventDAO(c).insert(marriageDate);
            //generate unique marriage eventID for father with fathers personID
            String eventID = new Event().generateEventID("Marriage");
            marriageDate.setEventID(eventID);
            marriageDate.setPersonID(father.getPersonID());
            new EventDAO(c).insert(marriageDate);
            eventsAdded += 2;

            //set values in person
            Person person;
            person = new Person(personID, associatedUsername, firstName, lastName, gender, father.getPersonID(), mother.getPersonID(), null);

            //save person in database
            new EventDAO(c).insert(birthDate);
            new EventDAO(c).insert(deathDate);
            new PersonDAO(c).insert(person);
            eventsAdded += 2;
            personsAdded += 1;

            String message = "Successfully added " + personsAdded + " persons and " + eventsAdded + " events to the database.";
            return new FillResult(message, true);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            db.closeConnection(false);
            FillResult result = new FillResult("Error: " + e.getMessage(), false);
            return result;
        }
    }

    private Person generatePerson( String gender, int generations, Connection c, int childsBday, String personID, String spouseID ) throws DataAccessException {
        Person mother = null;
        Person father = null;

        //generate all values of person here
        String firstName = generateFirstName(gender);
        String lastName = generateLastName(); //todo use fathers lastname here
        //String personID = new Person().generatePersonID(firstName);

        //generate events for person here
        //int childsBday = getkidsBday();
        Event birthDate = generateBirthdate(childsBday);
        Event deathDate = generateDeathDate(birthDate);
        birthDate.setPersonID(personID);
        deathDate.setPersonID(personID);


        if (generations >= 1) {
            String momID = new Person().generatePersonID("Mom");
            String dadID = new Person().generatePersonID("Dad");

            mother = generatePerson("F", generations - 1, c, birthDate.getYear(), momID, dadID);
            father = generatePerson("M", generations - 1, c, birthDate.getYear(), dadID, momID);

            //mother.setSpouseID(father.getPersonID());
            //father.setSpouseID(mother.getPersonID());

            //add marriage event to both mother and father
            Event marriageDate = generateMarriage(birthDate.getYear());//this is wrong, i believe
            marriageDate.setPersonID(mother.getPersonID());
            new EventDAO(c).insert(marriageDate);
            //generate unique marriage eventID for father with fathers personID
            String eventID = new Event().generateEventID("Marriage");
            marriageDate.setEventID(eventID);
            marriageDate.setPersonID(father.getPersonID());
            new EventDAO(c).insert(marriageDate);
            eventsAdded += 2;
        }


        //set values in person
        Person person;
        if (father == null) {
            person = new Person(personID, associatedUsername, firstName, lastName, gender, null, null, spouseID);
        } else {
            person = new Person(personID, associatedUsername, firstName, lastName, gender, father.getPersonID(), mother.getPersonID(), spouseID);
        }

        //save person in database
        new EventDAO(c).insert(birthDate);
        new EventDAO(c).insert(deathDate);
        new PersonDAO(c).insert(person);
        eventsAdded += 2;
        personsAdded += 1;

        return person;
    }


    private Event generateMarriage( int birthyear ) {
        //marriage is at between ages 20-60
        int marriageYear = getRandomDate(birthyear+20, birthyear+60);
        Location location = generateLocation();
        String eventID = new Event().generateEventID("Marriage");
        return new Event(eventID, associatedUsername, null,
                location.getLatitude(), location.getLongitude(),
                location.getCountry(), location.getCity(),
                "Marriage", marriageYear);
    }
    private Event generateDeathDate( Event birthDate ) {
        //death is at ages 60-120
        int birthYear = birthDate.getYear();
        int deathDate = getRandomDate(birthYear+60, birthYear+120);
        Location location = generateLocation();
        String eventID = new Event().generateEventID("Death");
        return new Event(eventID, associatedUsername, null,
                location.getLatitude(), location.getLongitude(),
                location.getCountry(), location.getCity(),
                "Death", deathDate);
    }
    private Event generateBirthdate( int kidsBday ) {
        //mom is between 13-50 years older than kid
        int birthdate = getRandomDate(kidsBday-50, kidsBday-14);
        Location location = generateLocation();
        String eventID = new Event().generateEventID("Birth");
        return new Event(eventID, associatedUsername, null,
                location.getLatitude(), location.getLongitude(),
                location.getCountry(), location.getCity(),
                "Birth", birthdate);
    }

    public int getRandomDate( int minYear, int maxYear ) {
        //minYear included, maxYear excluded
        //[minYear, maxYear)
        Random rand = new Random();
        int dif = maxYear - minYear;
        int val = rand.nextInt(dif);
        return val + minYear;
    }
    private String generateLastName() {
        return snames.getRandomName();
    }
    private String generateFirstName(String gender) {
        if (gender == "F") {
            return fNames.getRandomName();
        }
        return mnames.getRandomName();
    }
    private Location generateLocation() {
        return locations.getRandomLocation();
    }

    /**
     * default constructor
     */
    public FillService() {
    }
    public FillService( String associatedUsername ) {
        this.associatedUsername = associatedUsername;
    }
}
