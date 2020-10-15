package model.server;

/**
 * Person Data Structure
 */
public class Person {
    private String personID;
    private String descendant;
    private String firstName;
    private String lastName;
    private char gender;
    private String father;
    private String mother;
    private String spouse;

    /**
     * Person Constructor
     * @param id Person ID: Unique identifier for this person (non-empty string)
     * @param descendant User (Username) to which this person belongs
     * @param firstName Person’s first name (non-empty string)
     * @param lastName Person’s last name (non-empty string)
     * @param gender Person’s eventIcon (string: “f” or “m”)
     * @param father ID of person’s father (possibly null)
     * @param mother ID of person’s mother (possibly null)
     * @param spouse ID of person’s spouse (possibly null)
     */
    public Person(String id, String descendant, String firstName, String lastName, char gender, String father, String mother, String spouse) {
        this.personID = id;
        this.descendant = descendant;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.father = father;
        this.mother = mother;
        this.spouse = spouse;
    }

    public String getId() {
        return personID;
    }

    public void setId(String id) {
        this.personID = id;
    }

    public String getDescendant() {
        return descendant;
    }

    public void setDescendant(String descendant) {
        this.descendant = descendant;
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

    @Override
    public String toString() {
        return "Person{" +
                "id='" + personID + '\'' +
                ", descendant='" + descendant + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", eventIcon=" + gender +
                ", father='" + father + '\'' +
                ", mother='" + mother + '\'' +
                ", spouse='" + spouse + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (gender != person.gender) return false;
        if (!personID.equals(person.personID)) return false;
        if (!descendant.equals(person.descendant)) return false;
        if (!firstName.equals(person.firstName)) return false;
        if (!lastName.equals(person.lastName)) return false;
        if (father != null ? !father.equals(person.father) : person.father != null) return false;
        if (mother != null ? !mother.equals(person.mother) : person.mother != null) return false;
        return spouse != null ? spouse.equals(person.spouse) : person.spouse == null;
    }

    @Override
    public int hashCode() {
        return personID.hashCode();
    }
}
