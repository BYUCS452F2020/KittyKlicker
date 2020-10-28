package service;

import java.sql.SQLException;
import java.util.UUID;

import dao.DataBase;
import dao.PersonDao;
import dao.UserDao;
import model.AuthToken;
import model.Person;
import model.User;
import request.RegisterRequest;
import response.RegisterResponse;
import response.Response;

/**
 * Register Service
 */
public class RegisterService {

    private static DataBase db = new DataBase();

    private static final int DEFAULT_GENERATIONS = 4;

    /**
     * RegisterService Constructor
     */
    public RegisterService createService() {
        return null;
    }

    /**
     * The register method checks to see if the given username/password exist in the database
     * if not, then it creates a new User
     * @param request the login request containing a username and password
     */
    public static Response register(RegisterRequest request)
    {

        if (request.getUserName() == null || request.getPassword() == null || request.getEmail() == null || request.getFirstName() == null || request.getLastName() == null || request.getGender() == null)
        {
            return new Response("RegisterRequest property missing");
        }

        try
        {
            db.openConnection();
            if (UserDao.find(request.getUserName()) != null)
            {
                return new Response("Username is already taken.");
            }

            if (request.getGender().toCharArray().length != 1)
            {
                return new Response("Invalid gender. must be either f or m.");
            }
            else if (request.getGender().charAt(0) != 'f' && request.getGender().charAt(0) != 'm')
            {
                return new Response("Invalid gender. must be either f or m.");
            }

            String personID = UUID.randomUUID().toString().substring(0, 8);

            User user = new User(personID, request.getUserName(), request.getPassword(), request.getEmail(), request.getFirstName(), request.getLastName(), request.getGender().charAt(0));

            UserDao.insert(user);

            FillService.fill(user.getUserName(), DEFAULT_GENERATIONS);

            AuthToken auth = AuthService.generate(user.getUserName());

            return new RegisterResponse(auth.getToken(), user.getUserName(), user.getPersonID());
        }
        catch (Exception e)
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
