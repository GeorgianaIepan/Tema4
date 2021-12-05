package classe;

import java.util.*;

public class Lehrer extends Person {
    private List<Kurs> courses;

    public Lehrer(String firstName, String lastName, List<Kurs> courses) {
        super(firstName, lastName);
        this.courses = courses;
    }

    public List<Kurs> getCourses() {
        return courses;
    }

    public void setCourses(List<Kurs> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Lehrer{" +
                super.toString() +
                //"firstName = '" + super.getFirstName() + '\'' +
                //", lastName = '" + super.getLastName() + '\'' +
                ", courses = " + courses +
                '}';
    }
}
