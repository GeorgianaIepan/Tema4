package repository;

import classe.Student;

public class StudentRepository extends InMemoryRepository<Student> {

    public StudentRepository() {
        super();
    }

    @Override
    public Student update(Student obj) {
        Student updateStudent = this.inMemoryList.stream()
                .filter(student -> student.getStudentId() == obj.getStudentId())
                .findFirst()
                .orElseThrow();

        updateStudent.setFirstName(obj.getFirstName());
        updateStudent.setLastName(obj.getLastName());
        updateStudent.setTotalCredits(obj.getTotalCredits());
        updateStudent.setEnrolledCourse(obj.getEnrolledCourse());

        return updateStudent;
    }
}
