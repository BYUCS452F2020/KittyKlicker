package main.java.model;

/**
 * PowerUp Data Structure
 * @author Jared Swensen
 */
public class PowerUp {
    private String powerUpName;
    private String requirements;
    private String benefits;
    private String userID;

    public PowerUp(String powerUpName, String requirements, String benefits, String userID) {
        this.powerUpName = powerUpName;
        this.requirements = requirements;
        this.benefits = benefits;
        this.userID = userID;
    }

    public String getPowerUpName() {
        return powerUpName;
    }

    public void setPowerUpName(String powerUpName) {
        this.powerUpName = powerUpName;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
