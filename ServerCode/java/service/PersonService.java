package service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import dao.AuthTokenDao;
import dao.DataBase;
import dao.PersonDao;
import model.Person;
import request.PersonRequest;
import response.PersonResponse;
import response.PersonsResponse;
import response.Response;

/**
 * Created by jswense2 on 10/10/18.
 */

public class PersonService {

    private static DataBase db = new DataBase();
    /**
     * PersonService Constructor
     */
    public PersonService createService () {
        return null;
    }

    /**
     * find method: returns a person if there is an id,
     * if not it returns all family members of the current user determined by the auth token
     * @param request containing an auth token and possibly a person id
     */
    public static Response getPerson(PersonRequest request)
    {
        try
        {
            if (!AuthService.validate(request.getAuth()))
            {
                return new Response("Invalid Authorization");
            }
            db.openConnection();
            if (request.getId() != null)
            {
                Person person = PersonDao.find(request.getId());
                if (person == null)
                {
                    return new Response("Invalid personID");
                }
                if (!person.getDescendant().equals(AuthTokenDao.find(request.getAuth()).getUsername()))
                {
                    return new Response("Requested person does not belong to this user");
                }
                return new PersonResponse(person.getDescendant(), person.getId(), person.getFirstName(), person.getLastName(), person.getGender(), person.getFather(), person.getMother(), person.getSpouse());
            }

            Vector<PersonResponse> persons= new Vector<>();
            for (Person person : PersonDao.findAllFamily(AuthTokenDao.find(request.getAuth()).getUsername()))
            {
                PersonResponse p = new PersonResponse(person.getDescendant(), person.getId(), person.getFirstName(), person.getLastName(), person.getGender(), person.getFather(), person.getMother(), person.getSpouse());
                persons.add(p);
            }
            return new PersonsResponse(persons);
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
