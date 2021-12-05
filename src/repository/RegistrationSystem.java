package repository;

import classe.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RegistrationSystem {

    private final KursRepository courseFinalList;
    private final StudentRepository studentFinalList;
    private final LehrerRepository lehrerFinalList;

    public RegistrationSystem() {
        this.courseFinalList = new KursRepository();
        this.studentFinalList = new StudentRepository();
        this.lehrerFinalList = new LehrerRepository();
    }


    public KursRepository getCourseFinalList() {
        return courseFinalList;
    }

    public StudentRepository getStudentFinalList() {
        return studentFinalList;
    }

    public LehrerRepository getLehrerFinalList() {
        return lehrerFinalList;
    }

    /**
     * 1. studentul se inregistreaza la un curs
     *
     * @param course
     * @param student
     * @return true, daca toate conditiile sunt respectate
     */
    public boolean register(Kurs course, Student student) {
        boolean courseCheck = false;
        boolean studentCheck = false;

        for (int i = 0; i < this.courseFinalList.inMemoryList.size(); i++) {
            if (course.getName() == this.courseFinalList.inMemoryList.get(i).getName()) {
                courseCheck = true;
                break;
            }
        }

        for (int i = 0; i < this.studentFinalList.inMemoryList.size(); i++) {
            if (student.getStudentId() == this.studentFinalList.inMemoryList.get(i).getStudentId()) {
                studentCheck = true;
                break;
            }
        }

        int totalCredit = student.getTotalCredits() + course.getCredits();

        if (totalCredit > 30 || course.getStudentsEnrolled().size() > course.getMaxEnrollment()) {
            System.out.println("Nr de credite sau nr max de studenti este depasit ");
        } else {
            boolean courseListCheck = false;
            boolean studentListCheck = false;

            List<Kurs> courseList = new ArrayList<>();

            for (int i = 0; i < studentFinalList.inMemoryList.size(); i++) {
                if (studentFinalList.inMemoryList.get(i).getStudentId() == student.getStudentId()) {
                    for (int j = 0; j < studentFinalList.inMemoryList.get(i).getEnrolledCourse().size(); j++) {
                        if (course == studentFinalList.inMemoryList.get(i).getEnrolledCourse().get(j)) {
                            courseListCheck = true;
                            break;
                        }
                        courseList.add(studentFinalList.inMemoryList.get(i).getEnrolledCourse().get(j));
                    }
                }
            }

            if (courseListCheck = false) {
                courseList.add(course);
            }

            List<Student> studentList = new ArrayList<>();

            for (int i = 0; i < courseFinalList.inMemoryList.size(); i++) {
                if (courseFinalList.inMemoryList.get(i).getName() == course.getName()) {
                    for (int j = 0; j < courseFinalList.inMemoryList.get(i).getStudentsEnrolled().size(); j++) {
                        if (student == courseFinalList.inMemoryList.get(i).getStudentsEnrolled().get(j)) {
                            studentListCheck = true;
                            break;
                        }
                        studentList.add(courseFinalList.inMemoryList.get(i).getStudentsEnrolled().get(j));
                    }
                }
            }

            if (studentListCheck = false) {
                studentList.add(student);
            }

            Kurs newKurs = new Kurs(course.getName(), course.getTeacher(), course.getMaxEnrollment(), course.getStudentsEnrolled(), course.getCredits());
            courseFinalList.update(newKurs);

            Student newStudent = new Student(student.getFirstName(), student.getLastName(), student.getStudentId(), student.getTotalCredits(), student.getEnrolledCourse());
            studentFinalList.update(newStudent);
        }
        return true;
    }

    /**
     * 2. locurile disponibile la un curs
     *
     * @return kursPlatz
     */
    public HashMap<Kurs, Integer> retrieveCoursesWithFreePlaces() {

        HashMap<Kurs, Integer> kursPlatz = new HashMap<Kurs, Integer>();
        int platz = 0;

        for (Kurs kurs : courseFinalList.getAll()) {
            if (kurs.getMaxEnrollment() > kurs.getStudentsEnrolled().size()) {

                platz = kurs.getMaxEnrollment() - kurs.getStudentsEnrolled().size();
                kursPlatz.put(kurs, platz);
            }
        }

        return kursPlatz;
    }

    /**
     * 3. studentii inscrisi la un curs
     *
     * @param kurs
     * @return listStudents
     */
    public List<Student> retrieveStudentsEnrolledForACourse(Kurs kurs) {

        List<Student> listStudents = new ArrayList<>();

        for (int i = 0; i < this.courseFinalList.inMemoryList.size(); i++) {
            if (this.courseFinalList.inMemoryList.get(i).getName() == kurs.getName()) {

                listStudents.addAll(this.courseFinalList.inMemoryList.get(i).getStudentsEnrolled());
                break;
            }
        }

        return listStudents;
    }

    /**
     * 4. cursurile disponibile
     *
     * @return courseFinalList
     */
    public List<Kurs> getAllCourses() {
        return courseFinalList.getAll();
    }

    /**5. modificare credite
     *
     */


    /**6. stergere curs
     *
     */

}
