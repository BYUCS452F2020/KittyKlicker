//package test.dao;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.sql.SQLException;
//import java.util.List;
//
//import main.java.dao.DataBase;
//import dao.EventDao;
//import model.Event;
//
//import static org.junit.Assert.*;
//
///**
// * Created by jswense2 on 10/27/18.
// */
//
//public class EventDaoTest {
//
//    private EventDao testDao = new EventDao();
//    private DataBase db = new DataBase();
//    private Event object = new Event(
//            "testid",
//            "testusername",
//            "testPersonID",
//            0,
//            0,
//            "testCountry",
//            "testCity",
//            "testEventType",
//            2000);
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
//        assertEquals(testDao.find(object.getEventID()).toString(), object.toString());
//    }
//
//    @Test
//    public void findAllTest() throws SQLException
//    {
//        Event family1 = new Event(
//                "testid1",
//                "common",
//                "testPersonID",
//                0,
//                0,
//                "testCountry",
//                "testCity",
//                "testType",
//                0
//        );
//
//        Event family2 = new Event(
//                "testid2",
//                "common",
//                "testPersonID",
//                0,
//                0,
//                "testCountry",
//                "testCity",
//                "testType",
//                0
//        );
//
//        Event family3 = new Event(
//                "testid3",
//                "common",
//                "testPersonID",
//                0,
//                0,
//                "testCountry",
//                "testCity",
//                "testType",
//                0
//        );
//
//        Event notFamily = new Event(
//                "testid4",
//                "X",
//                "testPersonID",
//                0,
//                0,
//                "testCountry",
//                "testCity",
//                "testType",
//                0
//        );
//
//        Event[] family = {family1, family2, family3};
//
//        testDao.insert(family1);
//        testDao.insert(family2);
//        testDao.insert(family3);
//        testDao.insert(notFamily);
//
//        List<Event> foundFamily = testDao.findAllFamily("common");
//
//        assertTrue(foundFamily.size() == family.length);
//        for (Event person : family)
//        {
//            assertTrue(foundFamily.contains(person));
//        }
//    }
//
//    @Test
//    public void clearTest() throws SQLException
//    {
//        testDao.clear();
//        assertNull(testDao.find(object.getEventID()));
//    }
//
//
//}
