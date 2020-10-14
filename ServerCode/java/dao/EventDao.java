package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import model.Event;

import static dao.DataBase.executeQuery;
import static dao.DataBase.executeUpdate;

/**
 * Event Data Access Operator
 */
public class EventDao {

    private Connection conn;

    // used by database to open connection
    public void setConnection(Connection c)
    {
        conn = c;
    }

    /**
     * EventDao Constructor
     */
    public EventDao() {
    }

    /**
     * check to see if the given event id exists in the database
     * @param id the event id
     * @return the event object found, otherwise null
     */
    public static Event find(String id) throws SQLException
    {
        String query = "SELECT * FROM Events WHERE id = '" + id + "'";
        Event event = null;
        ResultSet r = executeQuery(query);
        if (r.next())
        {
            event = new Event(r.getString("id"), r.getString("Descendant"), r.getString("Person"), r.getDouble("Latitude"), r.getDouble("Longitude"), r.getString("Country"), r.getString("City"), r.getString("EventType"), r.getInt("Year"));
        }

        return event;
    }

    /**
     * return all the events for all family members of the current user
     * the current user is determined by the auth token id
     * @return a list of all family events
     */
    public static List<Event> findAllFamily(String username) throws SQLException
    {
        String query = "SELECT * FROM Events WHERE Descendant = '" + username + "'";
        Event event = null;
        List<Event> events = new Vector<>();
        ResultSet r = executeQuery(query);
        while (r.next())
        {
            event = new Event(r.getString("id"), r.getString("Descendant"), r.getString("Person"), r.getDouble("Latitude"), r.getDouble("Longitude"), r.getString("Country"), r.getString("City"), r.getString("EventType"), r.getInt("Year"));
            events.add(event);
        }

        return events;
    }

    /**
     * check to see if the given event id already exists
     * if not, then add it to the database
     * @param event the event object
     * @return true if the event was valid and added to the database
     */
    public static boolean insert(Event event) throws SQLException
    {
        String update = "INSERT INTO Events (id, Descendant, Person, Latitude, Longitude, Country, City, EventType, Year) VALUES ('" + event.getEventID() + "', '" + event.getDescendant() + "', '" + event.getPersonID() + "', '" + event.getLatitude() + "', '" + event.getLongitude() + "', '" + event.getCountry() + "', '" + event.getCity() + "', '" + event.getEventType() + "', '" + event.getYear() + "')";
        executeUpdate(update);
        return false;
    }

    /**
     * checks to see if the given event id exists
     * if it does, then remove it
     * @param id the event id
     * @return true if the event existed and was removed, otherwise false
     */
    public static boolean remove(String id) throws SQLException
    {
        String update = "DELETE FROM Events WHERE id = '" + id + "'";
        executeUpdate(update);

        return false;
    }

    // remove all event objects associated with the given username
    public static boolean removeFamily(String descendant) throws SQLException
    {
        String update = "DELETE FROM Events WHERE Descendant = '" + descendant + "'";
        executeUpdate(update);

        return false;
    }

    // clear the Events table
    public static boolean clear() throws SQLException
    {
        String update = "DELETE FROM Events";
        executeUpdate(update);

        return false;
    }
}
