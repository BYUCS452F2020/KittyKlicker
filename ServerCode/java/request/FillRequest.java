package request;

/**
 * Created by jswense2 on 10/10/18.
 */

public class FillRequest {
    private String username;
    private int generations;

    /**
     * FillRequest Constructor
     * @param username the username to generate data for with a default of 4 generations
     */
    public FillRequest(String username) {
        this.username = username;
    }

    /**
     * FillRequest Constructor with generations
     * @param username username to generate data for
     * @param generations number of generations to generate
     */
    public FillRequest(String username, int generations) {
        this.username = username;
        this.generations = generations;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGenerations() {
        return generations;
    }

    public void setGenerations(int generations) {
        this.generations = generations;
    }
}
