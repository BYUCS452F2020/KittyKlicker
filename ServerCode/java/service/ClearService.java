package service;

import java.sql.SQLException;

import dao.AuthTokenDao;
import dao.DataBase;
import dao.EventDao;
import dao.PersonDao;
import dao.UserDao;
import response.Response;

/**
 * Clear Service
 */
public class ClearService {

    private static DataBase db = new DataBase();
    /**
     * ClearService Constructor
     */

    /**
     * clear method: clears all data from the database
     */
    public static Response clear()
    {
        try
        {
            db.openConnection();
            AuthTokenDao.clear();
            UserDao.clear();
            PersonDao.clear();
            EventDao.clear();
        }
        catch (SQLException e)
        {
            return new Response(e.getMessage());
        }
        finally
        {
            try
            {
                db.closeConnection(true);
            }
            catch (SQLException e)
            {
                return new Response(e.getMessage());
            }
        }

        Response result = new Response();
        result.setMessage("Clear succeeded.");
        return result;
    }
}
