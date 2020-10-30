package model.request;

/**
 * @author Jared Swensen
 */

public class KlickRequest {
    private String auth;

    /**
     * KlickRequest Constructor requesting a klick for points
     * I'm thinking we can just use the auth token to query for the user we need
     * @param auth the auth token of the current user
     */
    public KlickRequest(String auth) {
        this.auth = auth;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
