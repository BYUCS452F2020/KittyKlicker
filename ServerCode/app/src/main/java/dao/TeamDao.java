package main.java.dao;

import main.java.model.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Team Data Access Operator
 */
public class TeamDao {

    private static DataBase db = DataBase.getInstance();

    /**
     * TeamDao Constructor
     */
    public TeamDao() {
    }

    /**
     * check to see if the given team id exists in the database
     * @param id the person id
     * @return the Person object found, otherwise null
     */
    public static Team find(String id) throws SQLException
    {
        String query = "SELECT * FROM Teams WHERE TeamID = ?";
        PreparedStatement stmt = db.getPreparedStatement(query);
        stmt.setString(1, id);
        Team team = null;
        ResultSet r = db.executeQuery(stmt);
        if (r.next())
        {
            team = new Team(r.getString("TeamID"), r.getString("Motto"), r.getInt("TotalKittiesKlicked"));
        }

        return team;
    }

    /**
     * check to see if the given team already exists
     * if not, then add the given team object to the database
     * @param team the team object to add
     * @return true if the person was valid and added
     */
    public static boolean insert(Team team) throws SQLException
    {
        String update = "INSERT INTO Teams (TeamId, Motto, TotalKittiesKlicked) VALUES (?, ?, ?)";
        PreparedStatement stmt = db.getPreparedStatement(update);
        stmt.setString(1, team.getTeamID());
        stmt.setString(1, team.getMotto());
        stmt.setInt(1, team.getTotalKittiesKlicked());
        db.executeUpdate(stmt);
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
        String update = "DELETE FROM Persons WHERE TeamId = ?";
        PreparedStatement stmt = db.getPreparedStatement(update);
        stmt.setString(1, id);
        db.executeUpdate(stmt);

        return false;
    }

    // clear the Persons table
    public static boolean clear() throws SQLException
    {
        String update = "DELETE FROM Teams";
        db.executeUpdate(db.getPreparedStatement(update));

        return false;
    }
}
