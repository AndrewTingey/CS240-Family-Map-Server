package DAO;

import Model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {
    private Database db;
    private User userA;
    private UserDAO uDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        userA = new User("at646", "12345", "at646@gmaiil", "Andy", "Ting", "M", "at646");
        Connection conn = db.getConnection();
        uDao = new UserDAO(conn);
        uDao.clear();
    }
    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        uDao.insert(userA);
        User compareUser = uDao.find(userA.getUsername());
        assertNotNull(compareUser);
        assertEquals(userA, compareUser);
    }

    @Test
    public void insertFail() throws DataAccessException {
        uDao.insert(userA);
        assertThrows(DataAccessException.class, () -> uDao.insert(userA));
    }

    @Test
    public void findPass() throws DataAccessException {
        User userB = new User("gtt", "67890", "gtt@gmaiil", "Grant", "Ting", "M", "gtt");
        //User userC = new User("gtt", "24689", "mtt@gmaiil", "Mitch", "Ting", "M", "mtt");

        uDao.insert(userA);
        uDao.insert(userB);

        User compareUser = uDao.find(userA.getUsername());
        assertNotNull(compareUser);
        assertEquals(userA, compareUser);
    }

    @Test
    public void findFail() throws DataAccessException {
        User compareUser = uDao.find("nonexistantUsername");
        assertNull(compareUser);
    }

    @Test
    public void clearPass() throws DataAccessException {
        User userB = new User("gtt", "67890", "gtt@gmaiil", "Grant", "Ting", "M", "gtt");

        uDao.insert(userA);
        uDao.insert(userB);

        uDao.clear();

        User compare = uDao.find(userA.getUsername());
        assertNull(compare);
        compare = uDao.find(userB.getUsername());
        assertNull(compare);
    }

    @Test
    public void clearAllPass() throws DataAccessException {
        User userB = new User("gtt", "67890", "gtt@gmaiil", "Grant", "Ting", "M", "gtt");

        uDao.insert(userA);
        uDao.insert(userB);

        uDao.clearAll("gtt");

        User compare = uDao.find(userA.getUsername());
        assertNotNull(compare);
        compare = uDao.find(userB.getUsername());
        assertNull(compare);
    }
}
