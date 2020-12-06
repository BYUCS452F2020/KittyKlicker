package dao;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static dao.PowerUpDao.*;

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

            int ls = getLowestScore();
            if (newScore < ls)
            {
                awardLowestScore(user.getUserID());
            }

            int hs = getHighestScore();
            if (newScore > hs)
            {
                awardHighestScore(user.getUserID());
            }

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
            int lun = getLongestUsername();

            String update = "INSERT INTO user (userID, password, kittiesKlicked, kittiesPerKlick, teamID) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(update);
            stmt.setString(1, user.getUserID());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getKittiesKlicked());
            stmt.setInt(4, user.getKittiesPerKlick());
            stmt.setString(5, user.getTeamName());
            db.executeUpdate(stmt);

            awardNewestPlayer(user.getUserID());

            if (user.getUserID().length() > lun)
            {
                awardLongestUsername(user.getUserID());
            }

            return true;
        } catch (Exception e) {
            System.err.println("Unable to insert user");
            return false;
        }
    }

    private static int getLowestScore() throws SQLException {
        try {
            String update = "SELECT kittiesKlicked FROM user order by kittiesKlicked ASC LIMIT 1";
            PreparedStatement stmt = db.getPreparedStatement(update);
            ResultSet r = db.executeQuery(stmt);
            if (r.next())
            {
                return r.getInt("kittiesKlicked");
            }
            else
            {
                return 0;
            }

        } catch (Exception e) {
            System.err.println("Unable get lowest score");
            return 0;
        }
    }

    private static int getHighestScore() throws SQLException {
        try {
            String update = "SELECT kittiesKlicked FROM user order by kittiesKlicked DESC LIMIT 1";
            PreparedStatement stmt = db.getPreparedStatement(update);
            ResultSet r = db.executeQuery(stmt);
            if (r.next())
            {
                return r.getInt("kittiesKlicked");
            }
            else
            {
                return 0;
            }

        } catch (Exception e) {
            System.err.println("Unable get highest score");
            return 0;
        }
    }

    private static int getLongestUsername() throws SQLException {
        try {
            String update = "SELECT LENGTH(userID) User_Length FROM user order by User_Length DESC LIMIT 1";
            PreparedStatement stmt = db.getPreparedStatement(update);
            ResultSet r = db.executeQuery(stmt);
            if (r.next())
            {
                return r.getInt("User_Length");
            }
            else
            {
                return 0;
            }

        } catch (Exception e) {
            System.err.println("Unable get longest username");
            return 0;
        }
    }



    private static void awardLowestScore(String userID) throws SQLException {
        try {
            // remove award from last newest player
            String update = "DELETE FROM powerup WHERE powerupID = ?";
            PreparedStatement stmt = db.getPreparedStatement(update);
            stmt.setString(1, LOWEST_SCORE.getPowerUpName());
            db.executeUpdate(stmt);

            // add award to newest player
            String update2 = "INSERT INTO powerup (powerupID, requirements, benefits, userID) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt2 = db.getPreparedStatement(update2);
            stmt2.setString(1, LOWEST_SCORE.getPowerUpName());
            stmt2.setString(2, LOWEST_SCORE.getRequirements());
            stmt2.setString(3, LOWEST_SCORE.getBenefits());
            stmt2.setString(4, userID);
            db.executeUpdate(stmt2);
        } catch (Exception e) {
            System.err.println("Unable to award lowest score: " + e.getMessage());
            throw e;
        }
    }

    private static void awardHighestScore(String userID) throws SQLException {
        try {
            // remove award from last newest player
            String update = "DELETE FROM powerup WHERE powerupID = ?";
            PreparedStatement stmt = db.getPreparedStatement(update);
            stmt.setString(1, HIGHEST_SCORE.getPowerUpName());
            db.executeUpdate(stmt);

            // add award to newest player
            String update2 = "INSERT INTO powerup (powerupID, requirements, benefits, userID) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt2 = db.getPreparedStatement(update2);
            stmt2.setString(1, HIGHEST_SCORE.getPowerUpName());
            stmt2.setString(2, HIGHEST_SCORE.getRequirements());
            stmt2.setString(3, HIGHEST_SCORE.getBenefits());
            stmt2.setString(4, userID);
            db.executeUpdate(stmt2);
        } catch (Exception e) {
            System.err.println("Unable to award highest score: " + e.getMessage());
            throw e;
        }
    }

    private static void awardNewestPlayer(String userID) throws SQLException {
        try {
            // remove award from last newest player
            String update = "DELETE FROM powerup WHERE powerupID = ?";
            PreparedStatement stmt = db.getPreparedStatement(update);
            stmt.setString(1, NEWEST_PLAYER.getPowerUpName());
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
            throw e;
        }
    }

    private static void awardLongestUsername(String userID) throws SQLException {
        try {
            // remove award from last newest player
            String update = "DELETE FROM powerup WHERE powerupID = ?";
            PreparedStatement stmt = db.getPreparedStatement(update);
            stmt.setString(1, LONGEST_USERNAME.getPowerUpName());
            db.executeUpdate(stmt);

            // add award to newest player
            String update2 = "INSERT INTO powerup (powerupID, requirements, benefits, userID) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt2 = db.getPreparedStatement(update2);
            stmt2.setString(1, LONGEST_USERNAME.getPowerUpName());
            stmt2.setString(2, LONGEST_USERNAME.getRequirements());
            stmt2.setString(3, LONGEST_USERNAME.getBenefits());
            stmt2.setString(4, userID);
            db.executeUpdate(stmt2);
        } catch (Exception e) {
            System.err.println("Unable to award longest username: " + e.getMessage());
            throw e;
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
