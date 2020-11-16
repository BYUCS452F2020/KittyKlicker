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
    private static final String dbname ="jdbc:sqlite:kitty.db";
    private static Connection conn;
    private static DataBase _dataBase;

    private DataBase() {
    }

    public static DataBase getInstance() {
        if (_dataBase == null) {
            _dataBase = new DataBase();
        }
        return _dataBase;
    }

    public PreparedStatement getPreparedStatement(String query) throws SQLException {
        return conn.prepareStatement(query);
    }

    // execute SQL Query and return result set
    public ResultSet executeQuery(PreparedStatement stmt) {
        try {
            return stmt.executeQuery();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    conn.rollback();
                } catch (SQLException excep) {
                    System.err.print("Transaction cannot roll back");
                }
            }
        }
        return null;
    }

    // execute SQL update with query
    public void executeUpdate(PreparedStatement stmt)
    {
        try {
            stmt.executeUpdate();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    conn.rollback();
                } catch (SQLException excep) {
                    System.err.print("Transaction cannot roll back");
                }
            }
        }
    }

    // open up a connection
    public void openConnection() throws SQLException
    {
        conn = DriverManager.getConnection(dbname);
        conn.setAutoCommit(false);
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
