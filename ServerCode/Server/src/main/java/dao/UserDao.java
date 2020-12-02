package dao;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static dao.PowerUpDao.NEWEST_PLAYER;

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
        String query = "SELECT * FROM user WHERE userID = ?";
        PreparedStatement stmt = db.getPreparedStatement(query);
        stmt.setString(1, id);
        User user = null;
        ResultSet r = db.executeQuery(stmt);
        if (r.next())
        {
            user = new User(r.getString("userId"), r.getString("password"), r.getInt("kittiesKlicked"), r.getInt("kittiesPerKlick"), r.getString("teamID"));
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
            String update = "UPDATE user SET kittiesKlicked = ? WHERE userID = ?";
            PreparedStatement stmt = db.getPreparedStatement(update);
            int newScore = user.getKittiesKlicked() + user.getKittiesPerKlick();
            stmt.setInt(1, newScore);
            stmt.setString(2, id);
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
    public static boolean insert(User user)
    {
        try {
            String update = "INSERT INTO user (userID, password, kittiesKlicked, kittiesPerKlick, teamID) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(update);
            stmt.setString(1, user.getUserID());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getKittiesKlicked());
            stmt.setInt(4, user.getKittiesPerKlick());
            stmt.setString(5, user.getTeamName());
            db.executeUpdate(stmt);

            awardNewestPlayer(user.getUserID());
            return true;
        } catch (Exception e) {
            System.err.println("Unable to insert user");
            return false;
        }
    }

    private static void awardNewestPlayer(String userID)
    {
        try {
            // remove award from last newest player
            String update = "DELETE FROM powerup WHERE userId = ? and powerupID = ?";
            PreparedStatement stmt = db.getPreparedStatement(update);
            stmt.setString(1, userID);
            stmt.setString(2, NEWEST_PLAYER.getPowerUpName());
            db.executeUpdate(stmt);

            // add award to newest player
            String update2 = "INSERT INTO powerup (powerupID, requirements, benefits, userID) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt2 = db.getPreparedStatement(update2);
            stmt2.setString(1, NEWEST_PLAYER.getPowerUpName());
            stmt2.setString(2, NEWEST_PLAYER.getRequirements());
            stmt2.setString(3, NEWEST_PLAYER.getBenefits());
            stmt2.setString(4, userID);
            db.executeUpdate(stmt2);
        } catch (Exception e) {
            System.err.println("Unable to award newest player: " + e.getMessage());
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
        String update = "DELETE FROM user WHERE userId = ?";
        PreparedStatement stmt = db.getPreparedStatement(update);
        stmt.setString(1, id);
        db.executeUpdate(stmt);

        return false;
    }

    // clear the user table
    public static boolean clear() throws SQLException
    {
        String update = "DELETE FROM user";
        PreparedStatement stmt = db.getPreparedStatement(update);
        db.executeUpdate(stmt);

        return false;
    }
}
