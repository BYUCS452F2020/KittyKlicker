package model.server;

/**
 * Created by jswense2 on 10/10/18.
 */

public class EventResponse extends Response{
    private String descendant;
    private String eventID;
    private String personID;
    private double latitude;
    private double longitude;
    private String country;
    private String city;
    private String eventType;
    private int year;

    /**
     * EventResponse Constructor
     * @param descendant Name of user account this event belongs to
     * @param eventID Event’s unique ID (non-empty string)
     * @param personID ID of the person this event belongs to (non-empty string)
     * @param latitude Latitude of the event’s location (number)
     * @param longitude Longitude of the event’s location (number)
     * @param country Name of country where event occurred (non-empty string)
     * @param city Name of city where event occurred (non-empty string)
     * @param eventType Type of event (“birth”, “baptism”, etc.) (non-empty string)
     * @param year Year the event occurred (integer)
     */
    public EventResponse(String descendant, String eventID, String personID, double latitude, double longitude, String country, String city, String eventType, int year) {
        this.descendant = descendant;
        this.eventID = eventID;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    public String getDescendant() {
        return descendant;
    }

    public void setDescendant(String descendant) {
        this.descendant = descendant;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
