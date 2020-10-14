package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.User;

import static dao.DataBase.executeQuery;
import static dao.DataBase.executeUpdate;

/**
 * UserDao Data Access Operator
 */
public class UserDao {

    private Connection conn;

    // used by the database to open a new connection
    public void setConnection(Connection c)
    {
        conn = c;
    }

    /**
     * UserDao Constructor
     */
    public UserDao() {
    }

    /**
     * check to see if the given user id exists in the database
     * @param username the user id
     * @return the user object if found, otherwise null
     */
    public static User find(String username) throws SQLException
    {
        String query = "SELECT * FROM Users WHERE Username = '" + username + "'";
        User user = null;
        ResultSet r = executeQuery(query);
        if (r.next())
        {
            user = new User(r.getString("id"), r.getString("Username"), r.getString("Password"), r.getString("Email"), r.getString("FirstName"), r.getString("LastName"), r.getString("Gender").charAt(0));
        }

        return user;
    }

    /**
     * return all the users
     * @return a list of all users
     */
    public static List<User> findAll()
    {
        return null;
    }

    /**
     * check to see if the given user id already exists
     * if not, then add the given user object to the database
     * @param user the user object
     * @return true if the user object was valid and added
     */
    public static boolean insert(User user) throws SQLException
    {
        String update = "INSERT INTO Users (id, Username, Password, Email, FirstName, LastName, Gender) VALUES ('" + user.getPersonID() + "', '" + user.getUserName() + "', '" + user.getPassword() + "', '" + user.getEmail() + "', '" + user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getGender() + "')";
        executeUpdate(update);

        return false;
    }

    /**
     * checks to see if the given user id exists
     * if it does, then remove it
     * @param id the user id
     * @return true if the user existed and was removed, otherwise false
     */
    public static boolean remove(String id) throws SQLException
    {
        String update = "DELETE FROM Users WHERE id = '" + id + "'";
        executeUpdate(update);

        return false;
    }

    // clear the Users table
    public static boolean clear() throws SQLException
    {
        String update = "DELETE FROM Users";
        executeUpdate(update);

        return false;
    }
}
