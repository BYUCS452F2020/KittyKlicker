package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.AuthToken;

/**
 * AuthToken Data Access Operator
 */
public class AuthTokenDao {

    private static dao.DataBase db = DataBase.getInstance();

    /**
     * AuthTokenDao Constructor
     */
    public AuthTokenDao()
    {

    }

    /**
     * check to see if the given username exists in the auth table of the database
     * @param token the auth token
     * @return the object found, otherwise null
     */
    public static AuthToken find(String token) throws SQLException
    {
        String query = "SELECT * FROM Auth WHERE Token = ?";
        PreparedStatement stmt = db.getPreparedStatement(query);
        stmt.setString(1, token);
        AuthToken auth = null;
        ResultSet r = db.executeQuery(stmt);
        if (r.next())
        {
            auth = new AuthToken(r.getString("UserId"), r.getString("Token"));
        }

        return auth;
    }

    /**
     * check to see if the given auth token already exists
     * if not, then add the given auth token to the database
     * @param authToken the auth token
     * @return true if the authToken was valid and was added
     */
    public static void insert(AuthToken authToken) throws SQLException
    {
        String update = "INSERT INTO Auth (Username, Token) VALUES (?, ?)";
        PreparedStatement stmt = db.getPreparedStatement(update);
        stmt.setString(1, authToken.getUsername());
        stmt.setString(2, authToken.getToken());
        db.executeUpdate(stmt);
    }

    /**
     * checks to see if the given auth token exists
     * if it does, then remove it
     * @param username username of the auth token
     * @return true if the auth token existed and was removed, otherwise false
     */
    public static void remove(String username) throws SQLException
    {
        String update = "DELETE FROM Auth WHERE Username = ?";
        PreparedStatement stmt = db.getPreparedStatement(update);
        stmt.setString(1, username);
        db.executeUpdate(stmt);
    }

    // validates the given token and returns a boolean as the result
    public static boolean validate(String token) throws SQLException
    {
        AuthToken auth = find(token);
        if (auth != null)
        {
            return true;
        }

        return false;
    }

    //clears Auth table in database
    public static void clear() throws SQLException
    {
        String update = "DELETE FROM Auth";
        db.executeUpdate(db.getPreparedStatement(update));
    }
}
