package model.response;

import model.response.Response;
import model.server.PowerUp;
import model.server.Team;
import model.server.User;

import java.util.List;

/**
 * @author Jared Swensen
 */

public class Login_RegisterResponse extends Response {
    private String authToken;
    private User user;
    private Team team;
    private List<PowerUp> powerups;

    public Login_RegisterResponse(String message) {
        super(message);
    }

    public Login_RegisterResponse(String authToken, User user, Team team, List<PowerUp> powerups) {
        this.authToken = authToken;
        this.user = user;
        this.team = team;
        this.powerups = powerups;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<PowerUp> getPowerups() {
        return powerups;
    }

    public void setPowerups(List<PowerUp> powerups) {
        this.powerups = powerups;
    }
}
