package classe;

import java.util.List;

public class Student extends Person {
    private long studentId;
    private int totalCredits;
    private List<Kurs> enrolledCourse;

    public Student(String firstName, String lastName, long studentId, int totalCredits, List<Kurs> enrolledCourse) {
        super(firstName, lastName);
        this.studentId = studentId;
        this.totalCredits = totalCredits;
        this.enrolledCourse = enrolledCourse;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public List<Kurs> getEnrolledCourse() {
        return enrolledCourse;
    }

    public void setEnrolledCourse(List<Kurs> enrolledCourse) {
        this.enrolledCourse = enrolledCourse;
    }

    @Override
    public String toString() {
        return "Student{" +
                super.toString() +
                //"firstName = '" + super.getFirstName() + '\'' +
                //", lastName = '" + super.getLastName() + '\'' +
                ", studentId = " + studentId +
                ", totalCredits = " + totalCredits +
                ", enrolledCourse = " + enrolledCourse +
                '}';
    }
}
