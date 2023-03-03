package DAO;

import Model.Authtoken;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class AuthTokenDAOTest {
    private Database db;
    private Authtoken authtoken;
    private AuthTokenDAO aDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        authtoken = new Authtoken("123abc", "at646");
        Connection conn = db.getConnection();
        aDao = new AuthTokenDAO(conn);
        aDao.clear();
    }

    @AfterEach
    public void tearDown() {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        aDao.insert(authtoken);
        Authtoken compare = aDao.find(authtoken.getAuthtoken());
        assertNotNull(compare);
        assertEquals(authtoken, compare);
    }
    @Test
    public void insertFail() throws DataAccessException {
        aDao.insert(authtoken);
        assertThrows(DataAccessException.class, () -> aDao.insert(authtoken));
    }

    @Test
    public void getPass() throws DataAccessException {
        aDao.insert(authtoken);
        Authtoken compare = aDao.find(authtoken.getAuthtoken());
        assertNotNull(compare);
        assertEquals(authtoken, compare);
    }
    @Test
    public void getFail() throws DataAccessException {
        Authtoken compare = aDao.find("nonexistent authtoken");
        assertNull(compare);
    }

    @Test
    public void clearPass() throws DataAccessException {
        aDao.insert(authtoken);
        Authtoken compare = aDao.find(authtoken.getAuthtoken());
        assertNotNull(compare);

        aDao.clear();
        compare = aDao.find(authtoken.getAuthtoken());
        assertNull(compare);
    }
}
