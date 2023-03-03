package DAO;

import Model.Event;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//We will use this to test that our insert method is working and failing in the right ways
public class EventDAOTest {
    private Database db;
    private Event bestEvent;
    private EventDAO eDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        // Here we can set up any classes or variables we will need for each test
        // lets create a new instance of the Database class
        db = new Database();
        // and a new event with random data
        bestEvent = new Event("Biking_123A", "Gale", "Gale123A",
                35.9f, 140.1f, "Japan", "Ushiku",
                "Biking_Around", 2016);

        // Here, we'll open the connection in preparation for the test case to use it
        Connection conn = db.getConnection();
        //Then we pass that connection to the EventDAO, so it can access the database.
        eDao = new EventDAO(conn);
        //Let's clear the database as well so any lingering data doesn't affect our tests
        eDao.clear();
    }

    @AfterEach
    public void tearDown() {
        // Here we close the connection to the database file, so it can be opened again later.
        // We will set commit to false because we do not want to save the changes to the database
        // between test cases.
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        // Start by inserting an event into the database.
        eDao.insert(bestEvent);
        // Let's use a find method to get the event that we just put in back out.
        Event compareTest = eDao.find(bestEvent.getEventID());
        // First lets see if our find method found anything at all. If it did then we know that we got
        // something back from our database.
        assertNotNull(compareTest);
        // Now lets make sure that what we put in is the same as what we got out. If this
        // passes then we know that our insert did put something in, and that it didn't change the
        // data in any way.
        // This assertion works by calling the equals method in the Event class.
        assertEquals(bestEvent, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        // Let's do this test again, but this time lets try to make it fail.
        // If we call the method the first time the event will be inserted successfully.
        eDao.insert(bestEvent);

        // However, our sql table is set up so that the column "eventID" must be unique, so trying to insert
        // the same event again will cause the insert method to throw an exception, and we can verify this
        // behavior by using the assertThrows assertion as shown below.

        // Note: This call uses a lambda function. A lambda function runs the code that comes after
        // the "()->", and the assertThrows assertion expects the code that ran to throw an
        // instance of the class in the first parameter, which in this case is a DataAccessException.
        assertThrows(DataAccessException.class, () -> eDao.insert(bestEvent));
    }

    @Test
    public void getPass() throws DataAccessException {
        Event eventB = new Event("Walking", "at646", "Gale123A",
                12.9f, 88.1f, "USA", "Las Vegas",
                "Walking_Around", 2001);
        Event eventC = new Event("Swimming", "at646", "Gale123A",
                16.9f, 18.1f, "USA", "Provo",
                "Swimming_Around", 21);

        eDao.insert(bestEvent);
        eDao.insert(eventB);
        eDao.insert(eventC);

        //Find one test
        Event compareEvent = eDao.find(bestEvent.getEventID());
        assertNotNull(compareEvent);
        assertEquals(bestEvent, compareEvent);

        //Find all test
        List<Event> eventList = new ArrayList<>();
        eventList.add(eventB);
        eventList.add(eventC);

        List<Event> compareList = eDao.findAll(eventB.getAssociatedUsername());
        assertNotNull(compareList);
        assertEquals(eventList, compareList);
    }

    @Test
    public void getFail() throws DataAccessException {
        Event compareEvent = eDao.find("Nonexistent eventID");
        assertNull(compareEvent);

        List<Event> compareEvents = eDao.findAll("nonexistentUsername");
        List<Event> emptyList = new ArrayList<>();
        assertEquals(emptyList, compareEvents);
    }

    @Test
    public void clearPass() throws DataAccessException {
        Event eventB = new Event("Walking", "at646", "Gale123A",
                12.9f, 88.1f, "USA", "Las Vegas",
                "Walking_Around", 2001);
        Event eventC = new Event("Swimming", "at646", "Gale123A",
                16.9f, 18.1f, "USA", "Provo",
                "Swimming_Around", 21);

        eDao.insert(bestEvent);
        eDao.insert(eventB);
        eDao.insert(eventC);

        //clear only some
        eDao.clearAll(eventB.getAssociatedUsername());

        Event compare = eDao.find(bestEvent.getEventID());
        assertNotNull(compare);
        compare = eDao.find(eventB.getEventID());
        assertNull(compare);
        compare = eDao.find(eventC.getEventID());
        assertNull(compare);

        //clear all
        eDao.clear();
        compare = eDao.find(bestEvent.getEventID());
        assertNull(compare);
    }
}
