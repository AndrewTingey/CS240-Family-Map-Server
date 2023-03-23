package DAO;

import Model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PersonDAOTest {
    private Database db;
    private Person personA;
    private PersonDAO pDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        personA = new Person("at646", "andrewtingey", "Andrew", "Tingey",
                "M", "ctt", "MSS", "ASd");//TRY ALSO WITH NULL FATHER, MOTHER, SPOUSE
        Connection conn = db.getConnection();
        pDao = new PersonDAO(conn);
        pDao.clear();
    }

    @AfterEach
    public void tearDown() {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        pDao.insert(personA);
        Person comparePerson = pDao.find(personA.getPersonID());
        assertNotNull(comparePerson);
        assertEquals(personA, comparePerson);
    }

    @Test
    public void insertFail() throws DataAccessException {
        pDao.insert(personA);
        assertThrows(DataAccessException.class, () -> pDao.insert(personA));
    }

    @Test
    public void findPass() throws DataAccessException {
        Person personB = new Person("gtt", "andrewtingey", "grant", "Tingey",
                "M", null, null, null);
        Person personC = new Person("notat646", "andrewtingey", "mitchell", "Tingey",
                "M", null, null, null);
        pDao.insert(personA);
        pDao.insert(personB);
        pDao.insert(personC);

        List<Person> peopleList = new ArrayList<>();
        peopleList.add(personA);
        peopleList.add(personB);
        peopleList.add(personC);

        Person comparePerson = pDao.find(personA.getPersonID());
        assertNotNull(comparePerson);
        assertEquals(personA, comparePerson);
    }

    @Test
    public void findAllPass() throws DataAccessException {
        Person personB = new Person("gtt", "andrewtingey", "grant", "Tingey",
                "M", null, null, null);
        Person personC = new Person("notat646", "andrewtingey", "mitchell", "Tingey",
                "M", null, null, null);
        pDao.insert(personA);
        pDao.insert(personB);
        pDao.insert(personC);

        List<Person> peopleList = new ArrayList<>();
        peopleList.add(personA);
        peopleList.add(personB);
        peopleList.add(personC);

        List<Person> comparePeople = pDao.findAll(personA.getAssociatedUsername());
        assertNotNull(comparePeople);
        assertEquals(peopleList, comparePeople);

    }

    @Test
    public void findFail() throws DataAccessException {
        Person comparePerson = pDao.find("NonexistantPersonID");
        assertNull(comparePerson);
    }

    @Test
    public void findAllFail() throws DataAccessException {
        List<Person> comparePeople = pDao.findAll("nonexistantAssociatedUsername");
        List<Person> emptyList = new ArrayList<>();
        assertEquals(emptyList, comparePeople);
    }

    @Test
    public void clearPass() throws DataAccessException {
        Person personB = new Person("gtt", "andrewtingey", "grant", "Tingey",
                "M", null, null, null);
        Person personC = new Person("notat646", "granttingey", "mitchell", "Tingey",
                "M", null, null, null);
        pDao.insert(personA);
        pDao.insert(personB);
        pDao.insert(personC);

        Person compare = pDao.find(personC.getPersonID());
        assertNotNull(compare);

        pDao.clear();
        compare = pDao.find(personC.getPersonID());
        assertNull(compare);
    }

    @Test
    public void clearAllPass() throws DataAccessException {
        Person personB = new Person("gtt", "andrewtingey", "grant", "Tingey",
                "M", null, null, null);
        Person personC = new Person("notat646", "granttingey", "mitchell", "Tingey",
                "M", null, null, null);
        pDao.insert(personA);
        pDao.insert(personB);
        pDao.insert(personC);

        pDao.clearAll(personB.getAssociatedUsername());

        Person compare = pDao.find(personA.getPersonID());
        assertNull(compare);
        compare = pDao.find(personB.getPersonID());
        assertNull(compare);
        //should not clear username granttingey
        compare = pDao.find(personC.getPersonID());
        assertNotNull(compare);
    }
}

