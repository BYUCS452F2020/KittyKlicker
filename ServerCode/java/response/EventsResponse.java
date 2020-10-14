package response;

import java.util.List;

/**
 * Created by jswense2 on 10/16/18.
 */

public class EventsResponse extends Response {
    private List<EventResponse> events;

    /**
     * response that sends a list of all events for all family members of the given auth token
     * @param events the list of event objects
     */
    public EventsResponse(List<EventResponse> events) {
        this.events = events;
    }

    public List<EventResponse> getEvents() {
        return events;
    }

    public void setEvents(List<EventResponse> events) {
        this.events = events;
    }
}
