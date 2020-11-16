package dao;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * UserDao Data Access Operator
 */
public class UserDao {

    private static DataBase db = DataBase.getInstance();

    /**
     * UserDao Constructor
     */
    public UserDao() {
    }

    /**
     * check to see if the given user id exists in the database
     * @param id the user id
     * @return the user object if found, otherwise null
     */
    public static User find(String id) throws SQLException
    {
        String query = "SELECT * FROM Users WHERE Username = ?";
        PreparedStatement stmt = db.getPreparedStatement(query);
        stmt.setString(1, id);
        User user = null;
        ResultSet r = db.executeQuery(stmt);
        if (r.next())
        {
            user = new User(r.getString("UserId"), r.getString("Password"), r.getInt("KittiesKlicked"), r.getInt("KittiesPerKlick"), r.getString("TeamID"));
        }

        return user;
    }

    /**
     * check to see if the given user id exists
     * if they do, update their klick score by one klick
     *
     * @param id the user id
     * @return the new total score
     */
    public static Integer klick(String id) throws SQLException
    {
        User user = find(id);

        if (user != null) {
            String update = "UPDATE Users SET KittiesKlicked = ? WHERE UserID = ?";
            PreparedStatement stmt = db.getPreparedStatement(update);
            int newScore = user.getKittiesKlicked() + user.getKittiesPerKlick();
            stmt.setInt(1, newScore);
            db.executeUpdate(stmt);
            return newScore;
        }

        return null;
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
        try {
            String update = "INSERT INTO Users (UserID, Password, KittiesKlicked, KittiesPerKlick, TeamID) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(update);
            stmt.setString(1, user.getUserID());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getKittiesKlicked());
            stmt.setInt(4, user.getKittiesPerKlick());
            stmt.setString(5, user.getTeamName());
            db.executeUpdate(stmt);
            return true;
        } catch (Exception e) {
            System.err.println("Unable to insert user");
            return false;
        }
    }

    /**
     * checks to see if the given user id exists
     * if it does, then remove it
     * @param id the user id
     * @return true if the user existed and was removed, otherwise false
     */
    public static boolean remove(String id) throws SQLException
    {
        String update = "DELETE FROM Users WHERE UserId = ?";
        PreparedStatement stmt = db.getPreparedStatement(update);
        stmt.setString(1, id);
        db.executeUpdate(stmt);

        return false;
    }

    // clear the Users table
    public static boolean clear() throws SQLException
    {
        String update = "DELETE FROM Users";
        PreparedStatement stmt = db.getPreparedStatement(update);
        db.executeUpdate(stmt);

        return false;
    }
}
