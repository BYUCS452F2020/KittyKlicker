//package test.dao;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.sql.SQLException;
//
//import main.java.dao.DataBase;
//import main.java.dao.UserDao;
//import main.java.model.User;
//
//import static org.junit.Assert.*;
//
///**
// * Created by jswense2 on 10/27/18.
// */
//
//public class UserDaoTest {
//
//    private UserDao testDao = new UserDao();
//    private DataBase db = new DataBase();
//    private User object = new User("testid", "testusername", "password", "email", "firstName", "lastName", 'm');
//
//    @Before
//    public void setUp() throws SQLException
//    {
//        db.openConnection();
//        testDao.insert(object);
//    }
//
//    @After
//    public void cleanUp() throws SQLException
//    {
//        db.closeConnection(false);
//    }
//
//    @Test
//    public void findTest() throws SQLException
//    {
//        assertEquals(testDao.find(object.getUserName()).toString(), object.toString());
//    }
//
//    @Test
//    public void clearTest() throws SQLException
//    {
//        testDao.clear();
//        assertNull(testDao.find(object.getUserName()));
//    }
//
//
//}
