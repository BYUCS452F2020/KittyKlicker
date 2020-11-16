package service;

import java.sql.SQLException;

import dao.AuthTokenDao;
import dao.DataBase;
import dao.PowerUpDao;
import dao.TeamDao;
import dao.UserDao;
import response.Response;

/**
 * Clear Service
 */
public class ClearService {

    private static DataBase db = DataBase.getInstance();
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
            PowerUpDao.clear();
            TeamDao.clear();
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
