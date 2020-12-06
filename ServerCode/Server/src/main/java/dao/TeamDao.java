package dao;

import model.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dao.PowerUpDao.LONGEST_TEAMNAME;

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
        String query = "SELECT * FROM team WHERE teamID = ?";
        PreparedStatement stmt = db.getPreparedStatement(query);
        stmt.setString(1, id);
        Team team = null;
        ResultSet r = db.executeQuery(stmt);
        if (r.next())
        {
            team = new Team(r.getString("teamID"), r.getString("motto"), r.getInt("totalKittiesKlicked"));
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
        int ltn = getLongestTeamName();

        String update = "INSERT INTO team (teamId, motto, totalKittiesKlicked) VALUES (?, ?, ?)";
        PreparedStatement stmt = db.getPreparedStatement(update);
        stmt.setString(1, team.getTeamID());
        stmt.setString(2, team.getMotto());
        stmt.setInt(3, team.getTotalKittiesKlicked());
        db.executeUpdate(stmt);

        if (team.getTeamID().length() > ltn)
        {
            awardLongestTeamName(team.getTeamID());
        }
        return false;
    }

    private static int getLongestTeamName() {
        try {
            String update = "SELECT LENGTH(teamID) Team_Length FROM team order by Team_Length DESC LIMIT 1";
            PreparedStatement stmt = db.getPreparedStatement(update);
            ResultSet r = db.executeQuery(stmt);
            if (r.next())
            {
                return r.getInt("Team_Length");
            }
            else
            {
                return 0;
            }

        } catch (Exception e) {
            System.err.println("Unable get longest team name");
            return 0;
        }
    }

    private static void awardLongestTeamName(String userID) throws SQLException {
        try {
            // remove award from last newest player
            String update = "DELETE FROM powerup WHERE powerupID = ?";
            PreparedStatement stmt = db.getPreparedStatement(update);
            stmt.setString(1, LONGEST_TEAMNAME.getPowerUpName());
            db.executeUpdate(stmt);

            // add award to newest player
            String update2 = "INSERT INTO powerup (powerupID, requirements, benefits, userID) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt2 = db.getPreparedStatement(update2);
            stmt2.setString(1, LONGEST_TEAMNAME.getPowerUpName());
            stmt2.setString(2, LONGEST_TEAMNAME.getRequirements());
            stmt2.setString(3, LONGEST_TEAMNAME.getBenefits());
            stmt2.setString(4, userID);
            db.executeUpdate(stmt2);
        } catch (Exception e) {
            System.err.println("Unable to award longest team name: " + e.getMessage());
            throw e;
        }
    }

    /**
     * checks to see if the given person id exists
     * if it does, then remove it
     * @param id the person id
     * @return true if the person existed and was removed, otherwise false
     */
    public boolean remove(String id) throws SQLException
    {
        String update = "DELETE FROM Persons WHERE teamId = ?";
        PreparedStatement stmt = db.getPreparedStatement(update);
        stmt.setString(1, id);
        db.executeUpdate(stmt);

        return false;
    }

    // clear the Persons table
    public static boolean clear() throws SQLException
    {
        String update = "DELETE FROM team";
        db.executeUpdate(db.getPreparedStatement(update));

        return false;
    }
}
