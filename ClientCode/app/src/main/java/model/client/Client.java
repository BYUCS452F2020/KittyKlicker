package model.client;

import android.util.Pair;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Polyline;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

import model.LoginRequest;
import model.RegisterRequest;
import model.server.EventResponse;
import model.server.PersonResponse;

/**
 * Created by jswense2 on 11/17/18.
 */

public class Client {

    private static Client client;

    private String host;
    private String port;
    private LoginRequest loginRequest;
    private RegisterRequest registerRequest;

    private EventResponse selectedEvent;
    private PersonResponse selectedPerson;

    // map of personID to the person
    private HashMap<String, PersonResponse> persons;
    // map of eventID to the event
    private HashMap<String, EventResponse> allEvents;
    // map of personID to list of that persons events
    private HashMap<String, Vector<EventResponse>> personsEvents;
    // map of personID to list of that persons children
    private HashMap<String, Vector<PersonResponse>> children;

    // filter activity
    private LinkedHashMap<String, Boolean> filters;
    // map that takes a personID and stores a boolean for whether or not it is on the fathers side
    private HashMap<String, Boolean> onFatherSide;

    // lines
    private String lifeStoryLinesColor;
    private String familyTreeLinesColor;
    private String spouseLinesColor;
    private HashSet<Polyline> lines;
    private boolean lifeSwitch;
    private boolean familySwitch;
    private boolean spouseSwitch;

    // google map
    private int mapType;

    // authorization
    private String auth;
    private String personID;

    // person activity
    private boolean lifeEventsVisible;
    private boolean familyVisible;

    // event activity
    private EventResponse selectedZoomEvent;
    private boolean zoomToSelectedEvent;

    // search activity
    private String search;

    public static Client getClient() {
        if (client == null)
            client = new Client();
        return client;
    }

    private Client() {
        persons = new HashMap<>();
        allEvents = new HashMap<>();
        personsEvents = new HashMap<>();
        children = new HashMap<>();
        lines = new HashSet<>();
        lifeStoryLinesColor = "black";
        familyTreeLinesColor = "green";
        spouseLinesColor = "red";
        lifeSwitch = true;
        familySwitch = true;
        spouseSwitch = true;
        mapType = GoogleMap.MAP_TYPE_NORMAL;
        selectedEvent = null;
        selectedPerson = null;
        lifeEventsVisible = true;
        familyVisible = true;
        zoomToSelectedEvent = false;

        filters = new LinkedHashMap<>();
        filters.put("Father's Side", true);
        filters.put("Mother's Side", true);
        filters.put("Male", true);
        filters.put("Female", true);
    }

    public void clear() {
        persons = new HashMap<>();
        allEvents = new HashMap<>();
        personsEvents = new HashMap<>();
        children = new HashMap<>();
        lines = new HashSet<>();
        lifeStoryLinesColor = "black";
        familyTreeLinesColor = "green";
        spouseLinesColor = "red";
        lifeSwitch = true;
        familySwitch = true;
        spouseSwitch = true;
        mapType = GoogleMap.MAP_TYPE_NORMAL;
        selectedEvent = null;
        selectedPerson = null;
        lifeEventsVisible = true;
        familyVisible = true;
        zoomToSelectedEvent = false;

        filters = new LinkedHashMap<>();
        filters.put("Father's Side", true);
        filters.put("Mother's Side", true);
        filters.put("Male", true);
        filters.put("Female", true);
    }

    public void sort() {
        for (Map.Entry<String, Vector<EventResponse>> eventsEntry : personsEvents.entrySet()) {
            Vector<EventResponse> events = new Vector<>(eventsEntry.getValue());
            Vector<EventResponse> sortedEvents = new Vector<>();
            int iterations = events.size();
            EventResponse death = null;
            for (int i = 0; i < iterations; i++) {
                EventResponse earliest = null;
                for (EventResponse e : events) {
                    if (e.getEventType().toLowerCase().equals("birth")) {
                        earliest = e;
                        break;
                    }
                    if (e.getEventType().toLowerCase().equals("death")) {
                        death = e;
                        events.remove(e);
                        break;
                    } else if (earliest == null) {
                        earliest = e;
                    } else if (e.getYear() < earliest.getYear()) {
                        earliest = e;
                    }
                }
                if (earliest != null) {
                    sortedEvents.add(earliest);
                    events.remove(earliest);
                }
            }
            if (death != null) {
                sortedEvents.add(death);
            }
            personsEvents.put(eventsEntry.getKey(), sortedEvents);
        }

        onFatherSide = new HashMap<>();
        // create a map for mothers side and fathers side
        // Father's Side
        Vector<String> FatherSide = getParents(persons.get(personID).getFather());
        FatherSide.add(persons.get(personID).getFather());
        for (String id : FatherSide)
        {
            onFatherSide.put(id, true);
        }

        // Mother's Side
        Vector<String> MotherSide = getParents(persons.get(personID).getMother());
        MotherSide.add(persons.get(personID).getMother());
        for (String id : MotherSide)
        {
            onFatherSide.put(id, false);
        }
    }

    private Vector<String> getParents(String personID)
    {
        Vector<String> result = new Vector<>();
        PersonResponse person = persons.get(personID);
        if (person.getFather() != null && !person.getFather().isEmpty() && !person.getFather().equals("null"))
        {
            result.add(person.getFather());
            Vector<String> FathersSide = getParents(person.getFather());
            result.addAll(FathersSide);
        }
        if (person.getMother() != null && !person.getMother().isEmpty() && !person.getMother().equals("null"))
        {
            result.add(person.getMother());
            Vector<String> MothersSide = getParents(person.getMother());
            result.addAll(MothersSide);
        }
        return result;
    }

    public LoginRequest getLoginRequest() {
        return loginRequest;
    }

    public void setLoginRequest(LoginRequest loginRequest) {
        this.loginRequest = loginRequest;
    }

    public RegisterRequest getRegisterRequest() {
        return registerRequest;
    }

    public void setRegisterRequest(RegisterRequest registerRequest) {
        this.registerRequest = registerRequest;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public HashMap<String, PersonResponse> getPersons() {
        return persons;
    }

    public void setPersons(HashMap<String, PersonResponse> persons) {
        this.persons = persons;
    }

    public HashMap<String, EventResponse> getAllEvents() {
        return allEvents;
    }

    public void setAllEvents(HashMap<String, EventResponse> allEvents) {
        this.allEvents = allEvents;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public HashMap<String, Vector<EventResponse>> getPersonsEvents() {
        return personsEvents;
    }

    public void setPersonsEvents(HashMap<String, Vector<EventResponse>> personsEvents) {
        this.personsEvents = personsEvents;
    }

    public HashSet<Polyline> getLines() {
        return lines;
    }

    public void setLines(HashSet<Polyline> lines) {
        this.lines = lines;
    }

    public EventResponse getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(EventResponse selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public PersonResponse getSelectedPerson() {
        return selectedPerson;
    }

    public void setSelectedPerson(PersonResponse selectedPerson) {
        this.selectedPerson = selectedPerson;
    }

    public String getLifeStoryLinesColor() {
        return lifeStoryLinesColor;
    }

    public void setLifeStoryLinesColor(String lifeStoryLinesColor) {
        this.lifeStoryLinesColor = lifeStoryLinesColor;
    }

    public String getFamilyTreeLinesColor() {
        return familyTreeLinesColor;
    }

    public void setFamilyTreeLinesColor(String familyTreeLinesColor) {
        this.familyTreeLinesColor = familyTreeLinesColor;
    }

    public String getSpouseLinesColor() {
        return spouseLinesColor;
    }

    public void setSpouseLinesColor(String spouseLinesColor) {
        this.spouseLinesColor = spouseLinesColor;
    }

    public boolean isLifeSwitch() {
        return lifeSwitch;
    }

    public void setLifeSwitch(boolean lifeSwitch) {
        this.lifeSwitch = lifeSwitch;
    }

    public boolean isFamilySwitch() {
        return familySwitch;
    }

    public void setFamilySwitch(boolean familySwitch) {
        this.familySwitch = familySwitch;
    }

    public boolean isSpouseSwitch() {
        return spouseSwitch;
    }

    public void setSpouseSwitch(boolean spouseSwitch) {
        this.spouseSwitch = spouseSwitch;
    }

    public int getMapType() {
        return mapType;
    }

    public void setMapType(int mapType) {
        this.mapType = mapType;
    }

    public boolean isLifeEventsVisible() {
        return lifeEventsVisible;
    }

    public void setLifeEventsVisible(boolean lifeEventsVisible) {
        this.lifeEventsVisible = lifeEventsVisible;
    }

    public boolean isFamilyVisible() {
        return familyVisible;
    }

    public void setFamilyVisible(boolean familyVisible) {
        this.familyVisible = familyVisible;
    }

    public HashMap<String, Vector<PersonResponse>> getChildren() {
        return children;
    }

    public void setChildren(HashMap<String, Vector<PersonResponse>> children) {
        this.children = children;
    }

    public boolean isZoomToSelectedEvent() {
        return zoomToSelectedEvent;
    }

    public void setZoomToSelectedEvent(boolean zoomToSelectedEvent) {
        this.zoomToSelectedEvent = zoomToSelectedEvent;
    }

    public EventResponse getSelectedZoomEvent() {
        return selectedZoomEvent;
    }

    public void setSelectedZoomEvent(EventResponse selectedZoomEvent) {
        this.selectedZoomEvent = selectedZoomEvent;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public LinkedHashMap<String, Boolean> getFilters() {
        return filters;
    }

    public void setFilters(LinkedHashMap<String, Boolean> filters) {
        this.filters = filters;
    }

    public HashMap<String, Boolean> getOnFatherSide() {
        return onFatherSide;
    }

    public void setOnFatherSide(HashMap<String, Boolean> onFatherSide) {
        this.onFatherSide = onFatherSide;
    }
}
