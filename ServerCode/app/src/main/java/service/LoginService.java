package service;

import dao.DataBase;
import dao.UserDao;
import model.AuthToken;
import model.User;
import request.LoginRequest;
import response.Response;

import java.sql.SQLException;

/**
 * Login Service
 */
public class LoginService {

    private static DataBase db = DataBase.getInstance();

    /**
     * LoginService Constructor
     */
    public LoginService createService () {
        return null;
    }

    /**
     * The login method checks to see if the given username/password exist in the database
     * uses the UserDao
     * @param request LoginRequest containing containing a username and password
     * @return returns a login response containing a authToken, username and password
     */
    public static Response login(LoginRequest request)
    {
        String username = request.getUserName();
        String password = request.getPassword();

        if (username == null || password == null)
        {
            return new Response("LoginRequest Property Missing.");
        }

        try
        {
            db.openConnection();
            User user = UserDao.find(username);
            
            if (user == null)
            {
                return new Response("User not found");
            }

            if (!password.equals(user.getPassword()))
            {
                return new Response("Invalid Password");
            }

            AuthToken auth = AuthService.generate(username);

            return new model.response.Login_RegisterResponse(auth.getToken(), user.getUserID(), user.getTeamName(), user.getKittiesKlicked(), user.getKittiesPerKlick(), null);
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
