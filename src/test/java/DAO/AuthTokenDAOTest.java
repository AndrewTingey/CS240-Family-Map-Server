package DAO;

import Model.Authtoken;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class AuthTokenDAOTest {
    private Database db;
    private Authtoken authtoken;
    private AuthTokenDAO aDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        authtoken = new Authtoken("123abc", "testing_andrew");
        Connection conn = db.getConnection();
        aDao = new AuthTokenDAO(conn);
        aDao.clearAll("testing_andrew");
    }

    @AfterEach
    public void tearDown() {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() {
        try {
            aDao.insert(authtoken);
            Authtoken compare = aDao.find(authtoken.getAuthtoken());
            assertNotNull(compare);
            assertEquals(authtoken, compare);
        } catch (DataAccessException e) {
            fail("Should not throw an exception");
        }
    }
    @Test
    public void insertFail() {
        try {
            //second insert of same authtoken
            aDao.insert(authtoken);
            assertThrows(DataAccessException.class, () -> aDao.insert(authtoken));
        } catch (DataAccessException e) {
            fail("Should not throw an exception");
        }
    }

    @Test
    public void insertByUsernamePass() {
        try {
            String authtoken = aDao.insertByUsername("testing_andrew");
            Authtoken compare = aDao.find(authtoken);
            assertNotNull(compare);
            assertEquals(authtoken, compare.getAuthtoken());
            assertEquals("testing_andrew", compare.getUsername());
        } catch (DataAccessException e) {
            fail("Should not throw an exception");
        }
    }

    @Test
    public void insertByUsernameFail() {
        try {
            //second insert of same username, should return unique authtokens
            String authtoken1 = aDao.insertByUsername("testing_andrew");
            assertNotEquals(authtoken1, aDao.insertByUsername("testing_andrew"));
        } catch (DataAccessException e) {
            fail("Should not throw an exception");
        }
    }
    @Test
    public void findPass() {
        try {
            aDao.insert(authtoken);
            Authtoken compare = aDao.find(authtoken.getAuthtoken());
            assertNotNull(compare);
            assertEquals(authtoken, compare);
        } catch (DataAccessException e) {
            fail("Should not throw an exception");
        }
    }
    @Test
    public void findFail() {
        try {
            Authtoken compare = aDao.find("nonexistent authtoken");
            assertNull(compare);
        } catch (DataAccessException e) {
            fail("Should not throw an exception");
        }
    }

    @Test
    public void findAuthTokenPass() {
        try {
            String authtoken = aDao.insertByUsername("testing_andrew");
            Authtoken compare = aDao.findAuthtoken("testing_andrew");
            assertNotNull(compare);
            assertEquals(authtoken, compare.getAuthtoken());
        } catch (DataAccessException e) {
            fail("Should not throw an exception");
        }
    }
    @Test
    public void findAuthTokenFail() {
        try {
            //username doesnt exist
            Authtoken compare = aDao.findAuthtoken("testing_andrew");
            assertNull(compare);
        } catch (DataAccessException e) {
            fail("Should not throw an exception");
        }
    }
    @Test
    public void clearPass() {
        try {
            aDao.insert(authtoken);
            Authtoken compare = aDao.find(authtoken.getAuthtoken());
            assertNotNull(compare);

            aDao.clear();
            compare = aDao.find(authtoken.getAuthtoken());
            assertNull(compare);
        } catch (DataAccessException e) {
            fail("Should not throw an exception");
        }
    }

    //clearAll
}
