package model.server;

/**
 * Created by jswense2 on 10/10/18.
 */

public class PersonResponse extends Response {
    private String descendant;
    private String personID;
    private String firstName;
    private String lastName;
    private char gender;
    private String father;
    private String mother;
    private String spouse;

    /**
     * PersonResponse Constructor
     * @param descendant Name of user account this person belongs to
     * @param personID Person's unique ID
     * @param firstName Person's first name
     * @param lastName Person's last name
     * @param gender Person's eventIcon ("m" or "f")
     * @param father ID of person’s father [OPTIONAL, can be missing]
     * @param mother ID of person’s mother [OPTIONAL, can be missing]
     * @param spouse ID of person’s spouse [OPTIONAL, can be missing]
     */
    public PersonResponse(String descendant, String  personID, String firstName, String lastName, char gender, String father, String mother, String spouse) {
        super();
        this.descendant = descendant;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.father = father;
        this.mother = mother;
        this.spouse = spouse;
    }

    public String getDescendant() {
        return descendant;
    }

    public void setDescendant(String descendant) {
        this.descendant = descendant;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }
}
