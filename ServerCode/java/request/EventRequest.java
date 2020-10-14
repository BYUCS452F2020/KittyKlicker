package request;

/**
 * Created by jswense2 on 10/10/18.
 */

public class EventRequest {
    private String id;
    private String auth;

    /**
     * EventRequest Constructor requesting all events for all family members of the current user
     * @param auth the auth token of the current user
     */
    public EventRequest(String auth) {
        this.auth = auth;
    }

    /**
     * EventRequest Constructor containing eventID request for that one event
     * @param id event id
     * @param auth auth token of the current user
     */
    public EventRequest(String id, String auth) {
        this.id = id;
        this.auth = auth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
