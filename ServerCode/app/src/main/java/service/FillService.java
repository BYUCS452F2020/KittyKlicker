//package main.java.service;
//
//import android.annotation.TargetApi;
//
//import com.google.gson.Gson;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.Reader;
//import java.sql.SQLException;
//import java.time.Year;
//import java.util.Random;
//import java.util.UUID;
//import java.util.Vector;
//
//import main.java.dao.DataBase;
//import dao.PersonDao;
//import main.java.dao.UserDao;
//import model.LocationData;
//import model.Event;
//import model.Locations;
//import model.Names;
//import model.Person;
//import main.java.model.User;
//import main.java.response.Response;
//
///**
// * Fill Service
// */
//@TargetApi(26)
//public class FillService {
//
//    private static Gson gson = new Gson();
//
//    private static DataBase db = new DataBase();
//
//    private static Names femaleNames;
//    private static Names maleNames;
//    private static Names surNames;
//    private static Locations locations;
//
//    private static Random rand = new Random();
//
//    private static int baseYear;
//
//    private static int peopleAdded;
//    private static int eventsAdded;
//
//    /**
//     * FillService Constructor
//     */
//    public FillService createService () {
//        return null;
//    }
//
//    /**
//     * fill method: generates and populates ancestor data for the specified user name
//     * (default generations is 4)
//     */
//    public static Response fillResponse(String username, int generations)
//    {
//        peopleAdded = 0;
//        eventsAdded = 0;
//        try
//        {
//            if (username == null || username.isEmpty())
//            {
//                return new Response("No Username");
//            }
//
//            db.openConnection();
//
//            if (null == UserDao.find(username))
//            {
//                return new Response("User not found");
//            }
//
//            PersonDao.removeFamily(username);
//            EventDao.removeFamily(username);
//
//            fill(username, generations);
//            return new Response("Successfully added " + peopleAdded + " persons and " + eventsAdded + " events to the database.");
//        }
//        catch (FileNotFoundException e)
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
//
//    private static final int SPACE_BETWEEN_GENERATIONS = 20;
//
//    /**
//     * this method is for use between services, so it doesn't return a response
//     */
//    public static void fill(String username, int generations) throws Exception
//    {
//        User user = UserDao.find(username);
//        Person userPerson = new Person(
//                user.getPersonID(),
//                user.getUserName(),
//                user.getFirstName(),
//                user.getLastName(),
//                user.getGender(),
//                null,
//                null,
//                null
//        );
//
//        LocationData location = getLocation();
//        Event birth = new Event(
//                UUID.randomUUID().toString().substring(0, 8),
//                user.getUserName(),
//                user.getPersonID(),
//                location.getLatitude(),
//                location.getLongitude(),
//                location.getCountry(),
//                location.getCity(),
//                "Birth",
//                baseYear - rand.nextInt(SPACE_BETWEEN_GENERATIONS) - rand.nextInt(VARIANCE_MAGNITUDE)
//        );
//
//        EventDao.insert(birth);
//        eventsAdded++;
//
//        Vector<Person> info = new Vector<>();
//        info.add(userPerson);
//        baseYear = Year.now().getValue();
//        Vector<Person> parents = new Vector<>();
//        // add descendant to parents collection in case there are 0 generations
//        parents.add(userPerson);
//        for (int i = 0; i < generations; i++)
//        {
//            parents.clear();
//            for (Person person : info)
//            {
//                parents.addAll(generateParents(person));
//            }
//            info.clear();
//            info.addAll(parents);
//            baseYear -= SPACE_BETWEEN_GENERATIONS;
//        }
//        for (Person par : parents)
//        {
//            PersonDao.insert(par);
//            peopleAdded++;
//        }
//    }
//
//    public static Vector<Person> generateParents(Person person) throws Exception
//    {
//
//        Reader fnames = new FileReader(new File(System.getProperty("user.dir") + "/json/fnames.json"));
//        femaleNames = gson.fromJson(fnames, Names.class);
//
//        Reader mnames = new FileReader(new File(System.getProperty("user.dir") + "/json/mnames.json"));
//        maleNames = gson.fromJson(mnames, Names.class);
//
//        Reader snames = new FileReader(new File(System.getProperty("user.dir") + "/json/snames.json"));
//        surNames = gson.fromJson(snames, Names.class);
//
//        String motherID = UUID.randomUUID().toString().substring(0, 8);
//        String fatherID = UUID.randomUUID().toString().substring(0, 8);
//
//        person.setMother(motherID);
//        person.setFather(fatherID);
//
//        PersonDao.insert(person);
//        peopleAdded++;
//
//        Person mother = new Person(
//                motherID,
//                person.getDescendant(),
//                femaleNames.getData()[rand.nextInt(femaleNames.getData().length)],
//                surNames.getData()[rand.nextInt(surNames.getData().length)],
//                'f',
//                null,
//                null,
//                fatherID
//        );
//
//        Person father = new Person(
//                fatherID,
//                person.getDescendant(),
//                maleNames.getData()[rand.nextInt(maleNames.getData().length)],
//                surNames.getData()[rand.nextInt(surNames.getData().length)],
//                'm',
//                null,
//                null,
//                motherID
//        );
//
//        LocationData location = getLocation();
//        Event marriage_mother = new Event(
//                UUID.randomUUID().toString().substring(0, 8),
//                person.getDescendant(),
//                motherID,
//                location.getLatitude(),
//                location.getLongitude(),
//                location.getCountry(),
//                location.getCity(),
//                "Marriage",
//                baseYear - rand.nextInt(SPACE_BETWEEN_GENERATIONS) - rand.nextInt(VARIANCE_MAGNITUDE)
//        );
//
//        Event marriage_father = new Event(
//                UUID.randomUUID().toString().substring(0, 8),
//                person.getDescendant(),
//                fatherID,
//                location.getLatitude(),
//                location.getLongitude(),
//                location.getCountry(),
//                location.getCity(),
//                "Marriage",
//                marriage_mother.getYear()
//        );
//
//        generateLifeEvents(motherID, person.getDescendant());
//        generateLifeEvents(fatherID, person.getDescendant());
//
//        EventDao.insert(marriage_mother);
//        eventsAdded++;
//        EventDao.insert(marriage_father);
//        eventsAdded++;
//
//        Vector<Person> result = new Vector<>();
//        result.add(mother);
//        result.add(father);
//        return result;
//    }
//
//    private static final int VARIANCE_MAGNITUDE = 4;
//    private static final int AVERAGE_LIFE_EXPECTANCY = 50;
//
//    public static void generateLifeEvents(String personID, String descendant) throws Exception
//    {
//
//        LocationData location = getLocation();
//        Event death = new Event(
//                UUID.randomUUID().toString().substring(0, 8),
//                descendant,
//                personID,
//                location.getLatitude(),
//                location.getLongitude(),
//                location.getCountry(),
//                location.getCity(),
//                "Death",
//                baseYear - rand.nextInt(VARIANCE_MAGNITUDE)
//                );
//
//        location = getLocation();
//        Event birth = new Event(
//                UUID.randomUUID().toString().substring(0, 8),
//                descendant,
//                personID,
//                location.getLatitude(),
//                location.getLongitude(),
//                location.getCountry(),
//                location.getCity(),
//                "Birth",
//                baseYear - AVERAGE_LIFE_EXPECTANCY - rand.nextInt(VARIANCE_MAGNITUDE)
//        );
//
//        location = getLocation();
//        Event baptism = new Event(
//                UUID.randomUUID().toString().substring(0, 8),
//                descendant,
//                personID,
//                location.getLatitude(),
//                location.getLongitude(),
//                location.getCountry(),
//                location.getCity(),
//                "Baptism",
//                birth.getYear() + 8
//        );
//
//        EventDao.insert(death);
//        eventsAdded++;
//        EventDao.insert(birth);
//        eventsAdded++;
//        EventDao.insert(baptism);
//        eventsAdded++;
//    }
//
//    public static LocationData getLocation() throws Exception
//    {
//        Reader locationFile = new FileReader(new File(System.getProperty("user.dir") + "/json/locations.json"));
//        locations = gson.fromJson(locationFile, Locations.class);
//        LocationData[] locals = locations.getData();
//        LocationData location = locals[rand.nextInt(locals.length)];
//
//        return location;
//    }
//}
