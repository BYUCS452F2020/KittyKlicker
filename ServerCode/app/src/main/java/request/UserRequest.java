package main.java.request;

/**
 * @author Jared Swensen
 */

public class UserRequest {
    private String auth;

    /**
     * UserRequest Constructor requesting user info for display
     * I'm thinking we can just use the auth token to query for the user we need
     * @param auth the auth token of the current user
     */
    public UserRequest(String auth) {
        this.auth = auth;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
