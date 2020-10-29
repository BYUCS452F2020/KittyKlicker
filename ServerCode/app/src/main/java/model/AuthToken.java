package main.java.model;

/**
 * AuthToken Data Structure
 * @author Jared Swensen
 */
public class AuthToken {
    private String username;
    private String token;

    /**
     * AuthToken Constructor
     * @param username the username associated with the auth token
     * @param token the auth token string
     */
    public AuthToken(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AuthToken{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
