package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import model.Person;

import static dao.DataBase.executeQuery;
import static dao.DataBase.executeUpdate;

/**
 * Person Data Access Operator
 */
public class PersonDao {

    private Connection conn;

    /**
     * PersonDao Constructor
     */
    public PersonDao() {
    }

    // used by the database to open a new connection
    public void setConnection(Connection c)
    {
        conn = c;
    }

    /**
     * check to see if the given person id exists in the database
     * @param id the person id
     * @return the Person object found, otherwise null
     */
    public static Person find(String id) throws SQLException
    {
        String query = "SELECT * FROM Persons WHERE id = '" + id + "'";
        Person person = null;
        ResultSet r = executeQuery(query);
        if (r.next())
        {
            person = new Person(r.getString("id"), r.getString("Descendant"), r.getString("FirstName"), r.getString("LastName"), r.getString("Gender").charAt(0), r.getString("Father"), r.getString("Mother"), r.getString("Spouse"));
        }

        return person;
    }

    /**
     * return all the Person objects for all family members of the current user
     * the current user is determined by the auth token id
     * @param descendant the common descendant of all the family person objects
     * @return a list of all family member Person objects
     */
    public static List<Person> findAllFamily(String descendant) throws SQLException
    {
        String query = "SELECT * FROM Persons WHERE Descendant = '" + descendant + "'";
        Person person = null;
        ResultSet r = executeQuery(query);
        List<Person> persons = new Vector<>();
        while (r.next())
        {
            person = new Person(r.getString("id"), r.getString("Descendant"), r.getString("FirstName"), r.getString("LastName"), r.getString("Gender").charAt(0), r.getString("Father"), r.getString("Mother"), r.getString("Spouse"));
            persons.add(person);
        }

        return persons;
    }

    /**
     * check to see if the given person already exists
     * if not, then add the given person object to the database
     * @param person the person object to add
     * @return true if the person was valid and added
     */
    public static boolean insert(Person person) throws SQLException
    {
        String update = "INSERT INTO Persons (id, Descendant, FirstName, LastName, Gender, Father, Mother, Spouse) VALUES ('" + person.getId() + "', '" + person.getDescendant() + "', '" + person.getFirstName() + "', '" + person.getLastName() + "', '" + person.getGender() + "', '" + person.getFather() + "', '" + person.getMother() + "', '" + person.getSpouse() + "')";
        executeUpdate(update);
        return false;
    }

    /**
     * checks to see if the given person id exists
     * if it does, then remove it
     * @param id the person id
     * @return true if the person existed and was removed, otherwise false
     */
    public boolean remove(String id) throws SQLException
    {
        String update = "DELETE FROM Persons WHERE personID = '" + id + "'";
        executeUpdate(update);

        return false;
    }

    // remove all Person objects associated with the given username
    public static boolean removeFamily(String descendant) throws SQLException
    {
        String update = "DELETE FROM Persons WHERE Descendant = '" + descendant + "'";
        executeUpdate(update);

        return false;
    }

    // clear the Persons table
    public static boolean clear() throws SQLException
    {
        String update = "DELETE FROM Persons";
        executeUpdate(update);

        return false;
    }
}
