package test.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import dao.AuthTokenDao;
import main.java.dao.DataBase;
import main.java.model.AuthToken;

import static org.junit.Assert.*;

/**
 * Created by jswense2 on 10/27/18.
 */

public class AuthTokenDaoTest {
    private AuthTokenDao testDao = new AuthTokenDao();
    private DataBase db = new DataBase();
    private AuthToken object = new AuthToken(
            "testusername",
            "testToken");

    private AuthToken invalidObject = new AuthToken(
            null,
            "testToken");

    @Before
    public void setUp() throws SQLException
    {
        db.openConnection();
        testDao.insert(object);
    }

    @After
    public void cleanUp() throws SQLException
    {
        db.closeConnection(false);
    }

    @Test
    public void findTest() throws SQLException
    {
        assertEquals(testDao.find(object.getToken()).toString(), object.toString());
    }

    @Test
    public void clearTest() throws SQLException
    {
        testDao.clear();
        assertNull(testDao.find(object.getToken()));
    }

    @Test
    public void validateTest() throws SQLException
    {
        assertTrue(testDao.validate(object.getToken()));
        assertFalse(testDao.validate("not a valid token"));
    }
}
