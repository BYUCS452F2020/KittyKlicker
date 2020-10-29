package main.java.request;

/**
 * @author Jared Swensen
 */

public class LoginRequest {
    private String userName;
    private String password;

    /**
     * LoginRequest Constructor
     * @param userName username of user
     * @param password password for given user
     */
    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() { return userName; }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
