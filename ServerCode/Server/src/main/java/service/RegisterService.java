package service;

import dao.*;
import model.AuthToken;
import model.PowerUp;
import model.Team;
import model.User;
import request.RegisterRequest;
import response.Login_RegisterResponse;
import response.Response;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

/**
* Register Service
*/
public class RegisterService {

    private static DataBase db = DataBase.getInstance();

    public static final String  QUOTES[] = {
            "Be yourself; everyone else is already taken.― Oscar Wilde",
            "A room without books is like a body without a soul. ― Marcus Tullius Cicero",
            "Be the change that you wish to see in the world. ― Mahatma Gandhi",
            "If you tell the truth, you don't have to remember anything. ― Mark Twain",
            "If you want to know what a man's like, take a good look at how he treats his inferiors, not his equals.― J.K. Rowling",
            "To live is the rarest thing in the world. Most people exist, that is all.― Oscar Wilde",
            "Without music, life would be a mistake. ― Friedrich Nietzsche",
            "Always forgive your enemies, nothing annoys them so much. ― Oscar Wilde",
            "Life isn't about getting and having, it's about giving and being. –Kevin Kruse",
            "Whatever the mind of man can conceive and believe, it can achieve. –Napoleon Hill",
            "Strive not to be a success, but rather to be of value. –Albert Einstein",
    };

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

           Team team = TeamDao.find(request.getTeamName());

           if (team == null)
           {
               Random rand = new Random();

               team = new Team(request.getTeamName(), QUOTES[rand.nextInt(QUOTES.length)], 0);
               TeamDao.insert(team);
           }

           User user = new User(request.getUserName(), request.getPassword(), 0, 1, request.getTeamName());

           UserDao.insert(user);

           List<PowerUp> user_powerups = PowerUpDao.find(user.getUserID());
           List<PowerUp> team_powerups = PowerUpDao.find(user.getTeamName());
           team_powerups.addAll(user_powerups);

           CouchBaseDB.openConnection();
           AuthToken auth = AuthService.generate(user.getUserID());
           CouchBaseDB.closeConnection();

           return new Login_RegisterResponse(auth.getToken(), user, team, team_powerups);
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
