package main.java.dao;

import main.java.model.PowerUp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Event Data Access Operator
 */
public class PowerUpDao {

    private static DataBase db = DataBase.getInstance();

    /**
     * PowerUpDao Constructor
     */
    public PowerUpDao() {
    }

    /**
     * check to see if a powerup exists in the database
     * @param id the powerup id
     * @return the powerup object found, otherwise null
     */
    public static PowerUp find(String id) throws SQLException
    {
        String query = "SELECT * FROM Powerups WHERE id = ?";
        PreparedStatement stmt = db.getPreparedStatement(query);
        stmt.setString(1, id);
        PowerUp powerup = null;
        ResultSet r = db.executeQuery(stmt);
        if (r.next())
        {
            powerup = new PowerUp(r.getString("PowerupID"), r.getString("Requirements"), r.getString("Benefits"), r.getString("UserID"));
        }

        return powerup;
    }

    /**
     * insert the given powerup object
     * @param power the powerup object
     */
    public static void insert(PowerUp power) throws SQLException
    {
        String update = "INSERT INTO Events (id, Descendant, Person, Latitude, Longitude, Country, City, EventType, Year) VALUES (?, ?, ?, ?)";
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
        String update = "DELETE FROM Powerups WHERE PowerupID = ?";
        PreparedStatement stmt = db.getPreparedStatement(update);
        stmt.setString(1, id);
        db.executeUpdate(stmt);
    }

    // clear the Powerup table
    public static void clear() throws SQLException
    {
        String update = "DELETE FROM Powerups";
        db.executeUpdate(db.getPreparedStatement(update));
    }
}
