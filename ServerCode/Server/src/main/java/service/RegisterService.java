package service;

import dao.DataBase;
import dao.UserDao;
import model.AuthToken;
import model.User;
import request.RegisterRequest;
import response.Login_RegisterResponse;
import response.Response;

import java.sql.SQLException;

/**
* Register Service
*/
public class RegisterService {

   private static DataBase db = DataBase.getInstance();

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

       if (request.getUserName() == null || request.getPassword() == null || request.getTeamName() == null)
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

           User user = new User(request.getUserName(), request.getPassword(), 0, 1, request.getTeamName());

           UserDao.insert(user);

           AuthToken auth = AuthService.generate(user.getUserID());

           return new Login_RegisterResponse(auth.getToken(), user.getUserID(), user.getTeamName(), user.getKittiesKlicked(), user.getKittiesPerKlick(), null);
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
