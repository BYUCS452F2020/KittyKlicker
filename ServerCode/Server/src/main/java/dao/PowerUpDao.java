package dao;

import model.PowerUp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Event Data Access Operator
 */
public class PowerUpDao {

    public static final PowerUp NEWEST_PLAYER = new PowerUp(
            "Newest Player",
            "You are the most recent person to make a Kitty Klicker account.",
            "You are special.",
            null);
    public static final PowerUp LOWEST_SCORE = new PowerUp(
            "Lowest Score",
            "You have the lowest score of anyone that has ever played Kitty Klicker, wow.",
            "+1 Klicking Power! Stick it to the man!",
            null);
    public static final PowerUp HIGHEST_SCORE = new PowerUp(
            "Highest Score",
            "You have the highest score! You're top dawg!",
            "You are so talented.",
            null);
    public static final PowerUp LONGEST_USERNAME = new PowerUp(
            "Longest Username",
            "You have the loooooooooongest username of anyone.",
            "You are sooooo special.",
            null);
    public static final PowerUp LONGEST_TEAMNAME = new PowerUp(
            "Longest Team Name",
            "Youre team has the looooooooooooongest team name of anyone.'",
            "Your team is the most special.",
            null);

    private static DataBase db = DataBase.getInstance();

    /**
     * PowerUpDao Constructor
     */
    public PowerUpDao() {
    }

    /**
     * check to see if a user or team has any powerups in the database
     * @return the powerup object found, otherwise null
     */
    public static List<PowerUp> find(String id) throws SQLException
    {
        String query = "SELECT * FROM powerup WHERE userID = ?";
        PreparedStatement stmt = db.getPreparedStatement(query);
        stmt.setString(1, id);

        List<PowerUp> result = new ArrayList<>();
        ResultSet r = db.executeQuery(stmt);
        while (r.next())
        {
            PowerUp powerup = null;
            powerup = new PowerUp(r.getString("powerupID"), r.getString("requirements"), r.getString("benefits"), r.getString("userID"));
            result.add(powerup);
        }

        return result;
    }

    /**
     * insert the given powerup object
     * @param power the powerup object
     */
    public static void insert(PowerUp power) throws SQLException
    {
        String update = "INSERT INTO powerup (powerupID, requirements, benefits, userID) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = db.getPreparedStatement(update);
        stmt.setString(1, power.getPowerUpName());
        stmt.setString(2, power.getRequirements());
        stmt.setString(3, power.getBenefits());
        stmt.setString(4, power.getUserID());
        db.executeUpdate(stmt);
    }

    /**
     * remove the powerup with the given id
     * @param id the powerup id
     */
    public static void remove(String id) throws SQLException
    {
        String update = "DELETE FROM powerup WHERE powerupID = ?";
        PreparedStatement stmt = db.getPreparedStatement(update);
        stmt.setString(1, id);
        db.executeUpdate(stmt);
    }

    // clear the Powerup table
    public static void clear() throws SQLException
    {
        String update = "DELETE FROM powerup";
        db.executeUpdate(db.getPreparedStatement(update));
    }
}
