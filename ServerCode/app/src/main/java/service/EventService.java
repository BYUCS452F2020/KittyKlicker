package main.java.service;

import java.sql.SQLException;
import java.util.Vector;

import dao.AuthTokenDao;
import main.java.dao.DataBase;
import model.Event;
import request.EventRequest;
import response.EventResponse;
import response.EventsResponse;
import main.java.response.Response;

/**
 * Created by jswense2 on 10/10/18.
 */

public class EventService {

    private static DataBase db = new DataBase();

    /**
     * EventService Constructor
     */
    public EventService createService () {
        return null;
    }

    /**
     * getEvent method:
     * if there is an Event id
     * it returns the single event object with specified id
     * else
     * it returns all events for all family members of the current user. Current user is determined from auth token
     * @param request EventRequest object containing auth token and possibly an eventID
     */
    public static Response getEvent(EventRequest request)
    {
        if (!AuthService.validate(request.getAuth()))
        {
            return new Response("Invalid Authorization");
        }
        try
        {
            db.openConnection();
            if (request.getId() != null)
            {
                Event event = EventDao.find(request.getId());
                if (event == null)
                {
                    return new Response("Invalid personID");
                }
                if (!event.getDescendant().equals(AuthTokenDao.find(request.getAuth()).getUsername()))
                {
                    return new Response("Requested person does not belong to this user");
                }
                return new EventResponse(event.getDescendant(), event.getEventID(), event.getPersonID(), event.getLatitude(), event.getLongitude(), event.getCountry(), event.getCity(), event.getEventType(), event.getYear());
            }

            Vector<EventResponse> events = new Vector<>();
            for (Event event : EventDao.findAllFamily(AuthTokenDao.find(request.getAuth()).getUsername()))
            {
                EventResponse e = new EventResponse(event.getDescendant(), event.getEventID(), event.getPersonID(), event.getLatitude(), event.getLongitude(), event.getCountry(), event.getCity(), event.getEventType(), event.getYear());
                events.add(e);
            }
            return new EventsResponse(events);
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
