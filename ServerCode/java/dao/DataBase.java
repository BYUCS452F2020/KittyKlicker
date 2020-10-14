package dao;

/**
 * Created by jswense2 on 10/16/18.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBase {
    private static final String dbname ="jdbc:sqlite:newdatabase.db";
    private static Connection conn;
    private static PreparedStatement stmt;

    private UserDao userDao;
    private PersonDao personDao;
    private EventDao eventDao;
    private AuthTokenDao authTokenDao;

    public DataBase() {
        this.userDao = new UserDao();
        this.personDao = new PersonDao();
        this.eventDao = new EventDao();
        this.authTokenDao = new AuthTokenDao();
    }

    // execute SQL Query and return result set
    public static ResultSet executeQuery(String query) throws SQLException
    {
        stmt = conn.prepareStatement(query);
        ResultSet result = stmt.executeQuery();
        return result;
    }

    // execute SQL update with query
    public static void executeUpdate(String query) throws SQLException
    {
        stmt = conn.prepareStatement(query);
        stmt.executeUpdate();
    }

    // open up a connection
    public void openConnection() throws SQLException
    {
        conn = DriverManager.getConnection(dbname);
        conn.setAutoCommit(false);
        userDao.setConnection(conn);
        personDao.setConnection(conn);
        eventDao.setConnection(conn);
        authTokenDao.setConnection(conn);
    }

    // close the connection
    public void closeConnection(boolean commit) throws SQLException
    {
        if (commit) {
            conn.commit();
        }
        else {
            conn.rollback();
        }

        conn.close();
        conn = null;
    }
}
