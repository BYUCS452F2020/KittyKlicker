package service;

import java.sql.SQLException;

import dao.AuthTokenDao;
import dao.DataBase;
import dao.UserDao;
import model.AuthToken;
import model.User;
import request.LoginRequest;
import response.LoginResponse;
import response.Response;

/**
 * Login Service
 */
public class LoginService {

    private static DataBase db = new DataBase();

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

            return new LoginResponse(auth.getToken(), user.getUserName(), user.getPersonID());
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
