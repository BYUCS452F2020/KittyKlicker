package test.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import main.java.dao.DataBase;
import dao.PersonDao;
import model.Person;

import static org.junit.Assert.*;

/**
 * Created by jswense2 on 10/27/18.
 */

public class PersonDaoTest {

    private PersonDao testDao = new PersonDao();
    private DataBase db = new DataBase();
    private Person object = new Person(
            "testid",
            "testusername",
            "testFirstName",
            "testLastName",
            'm',
            "father",
            "mother",
            "spouse");

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
        assertEquals(testDao.find(object.getId()).toString(), object.toString());
    }

    @Test
    public void findAllTest() throws SQLException
    {
        Person family1 = new Person(
                "testid1",
                "common",
                "testFirstName",
                "testLastName",
                'm',
                "father",
                "mother",
                "spouse"
        );

        Person family2 = new Person(
                "testid2",
                "common",
                "testFirstName",
                "testLastName",
                'm',
                "father",
                "mother",
                "spouse"
        );

        Person family3 = new Person(
                "testid3",
                "common",
                "testFirstName",
                "testLastName",
                'm',
                "father",
                "mother",
                "spouse"
        );

        Person notFamily = new Person(
                "testid4",
                "X",
                "testFirstName",
                "testLastName",
                'm',
                "father",
                "mother",
                "spouse"
        );

        Person[] family = {family1, family2, family3};

        testDao.insert(family1);
        testDao.insert(family2);
        testDao.insert(family3);
        testDao.insert(notFamily);

        List<Person> foundFamily = testDao.findAllFamily("common");

        assertTrue(foundFamily.size() == family.length);
        for (Person person : family)
        {
            assertTrue(foundFamily.contains(person));
        }
    }

    @Test
    public void clearTest() throws SQLException
    {
        testDao.clear();
        assertNull(testDao.find(object.getId()));
    }
}
