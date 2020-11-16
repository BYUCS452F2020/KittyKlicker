package service;//package main.java.service;
//
//import java.sql.SQLException;
//
//import main.java.dao.DataBase;
//import dao.PersonDao;
//import main.java.dao.UserDao;
//import model.Event;
//import model.Person;
//import main.java.model.User;
//import request.LoadRequest;
//import main.java.response.Response;
//
///**
// * Created by jswense2 on 10/10/18.
// */
//
//public class LoadService {
//
//    private static DataBase db = new DataBase();
//
//    /**
//     * LoadService Constructor
//     */
//    public LoadService createService () {
//        return null;
//    }
//
//    /**
//     * load method: clears the database and loads the given data into the database
//     * @param request LoadRequest containing the new user, person, and event data to load into the database
//     */
//    public static Response load(LoadRequest request)
//    {
//        try
//        {
//            ClearService.clear();
//            db.openConnection();
//            for (User user : request.getUsers())
//            {
//                UserDao.insert(user);
//            }
//
//            for (Person person : request.getPersons())
//            {
//                PersonDao.insert(person);
//            }
//
//            for (Event event : request.getEvents())
//            {
//                EventDao.insert(event);
//            }
//
//            String result = "Successfully added " + request.getUsers().length + " users, " + request.getPersons().length + " persons, and " + request.getEvents().length + " events to the database.";
//            result.replace("\\n", "").replace("\\r", "");
//
//            return new Response(result);
//        }
//        catch (SQLException e)
//        {
//            return new Response(e.getMessage());
//        }
//        catch (Exception e)
//        {
//            return new Response(e.getMessage());
//        }
//        finally
//        {
//            try
//            {
//                db.closeConnection(true);
//            }
//            catch (SQLException e)
//            {
//                return new Response(e.getMessage());
//            }
//        }
//    }
//}
