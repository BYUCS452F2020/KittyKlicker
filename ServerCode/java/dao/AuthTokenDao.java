package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.AuthToken;

import static dao.DataBase.executeQuery;
import static dao.DataBase.executeUpdate;

/**
 * AuthToken Data Access Operator
 */
public class AuthTokenDao {

    private Connection conn;

    public void setConnection(Connection c)
    {
        conn = c;
    }
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
        String query = "SELECT * FROM Auth WHERE Token = '" + token + "'";
        AuthToken auth = null;
        ResultSet r = executeQuery(query);
        if (r.next())
        {
            auth = new AuthToken(r.getString("Username"), r.getString("Token"));
        }

        return auth;
    }

    /**
     * check to see if the given auth token already exists
     * if not, then add the given auth token to the database
     * @param authToken the auth token
     * @return true if the authToken was valid and was added
     */
    public static boolean insert(AuthToken authToken) throws SQLException
    {
        String update = "INSERT INTO Auth (Username, Token) VALUES ('" + authToken.getUsername() + "', '" + authToken.getToken() + "')";
        executeUpdate(update);
        return false;
    }

    /**
     * checks to see if the given auth token exists
     * if it does, then remove it
     * @param username username of the auth token
     * @return true if the auth token existed and was removed, otherwise false
     */
    public static boolean remove(String username) throws SQLException
    {
        String update = "DELETE FROM Auth WHERE Username = '" + username + "'";
        executeUpdate(update);

        return false;
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
    public static boolean clear() throws SQLException
    {
        String update = "DELETE FROM Auth";
        executeUpdate(update);

        return false;
    }
}
