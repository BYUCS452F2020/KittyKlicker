package response;

import java.util.List;

import model.Person;

/**
 * Created by jswense2 on 10/16/18.
 */

public class PersonsResponse extends Response {
    private List<PersonResponse> persons;

    /**
     * response including the list of all family member
     * person objects associated with the give authtoken
     * @param persons a list of person objects
     */
    public PersonsResponse(List<PersonResponse> persons) {

        this.persons = persons;
    }

    public List<PersonResponse> getPersons() {
        return persons;
    }


    public void setPersons(List<PersonResponse> persons) {
        this.persons = persons;
    }
}
