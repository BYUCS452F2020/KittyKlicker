package request;

import java.lang.reflect.Array;

import model.Event;
import model.Person;
import model.User;

/**
 * Created by jswense2 on 10/8/18.
 *
 * The “users” property in the request body contains an array of users to be
 * created. The “persons” and “events” properties contain family history information for these
 * users.  The objects contained in the “persons” and “events” arrays should be added to the
 * server’s database.  The objects in the “users” array have the same format as those passed to
 * the /user/register API with the addition of the personID.  The objects in the “persons” array have
 * the same format as those returned by the /person/[personID] API.  The objects in the “events”
 * array have the same format as those returned by the /event/[eventID] API.
 *
 * Errors: Invalid request data (missing values, invalid values, etc.), Internal server error
 */

public class LoadRequest {
    private User[] users;
    private Person[] persons;
    private Event[] events;

    /**
     * LoadRequest Constructor
     * @param users array of user objects
     * @param persons array of person objects
     * @param events array of event objects
     */
    public LoadRequest(User[] users, Person[] persons, Event[] events) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Person[] getPersons() {
        return persons;
    }

    public void setPersons(Person[] persons) {
        this.persons = persons;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }
}
