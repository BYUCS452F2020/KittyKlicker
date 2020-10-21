package model;

import model.server.Response;

/**
 * Created by jswense2 on 10/8/18.
 */

public class Login_RegisterResponse extends Response {
    private String authToken;
    private String userName;
    private String personID;

    public Login_RegisterResponse(String error)
    {
        setMessage(error);
    }

    /**
     * LoginResponse Constructor
     * @param authToken Non-empty auth token string
     * @param userName User name passed in with request
     * @param personID Non-empty string containing the Person ID of the user’s generated Person object
     */
    public Login_RegisterResponse(String authToken, String userName, String personID) {
        super();
        this.authToken = authToken;
        this.userName = userName;
        this.personID = personID;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}
