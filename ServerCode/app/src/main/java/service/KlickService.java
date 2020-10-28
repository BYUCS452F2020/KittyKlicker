package main.java.service;

import main.java.dao.DataBase;
import main.java.request.KlickRequest;
import main.java.response.KlickResponse;
import main.java.response.Response;

import java.sql.SQLException;

public class KlickService {

    private static DataBase db = DataBase.getInstance();

    /**
     * PersonService Constructor
     */
    public KlickService createService () {
        return null;
    }

    /**
     * klick handles a klick event
     * @param request KlickRequest containing an auth token
     */
    public static Response klick(KlickRequest request)
    {
        try
        {
            if (!AuthService.validate(request.getAuth()))
            {
                return new Response("Invalid Authorization");
            }
            db.openConnection();

            //TODO: Handle klicks and return the new total
            return new KlickResponse(10);
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
    }
}
