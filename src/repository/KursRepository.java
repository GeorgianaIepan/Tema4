package repository;

import classe.Kurs;

public class KursRepository extends InMemoryRepository<Kurs> {

    public KursRepository() {
        super();
    }

    @Override
    public Kurs update(Kurs obj) {
        Kurs updateKurs = this.inMemoryList.stream()
                .filter(kurs -> kurs.getName() == obj.getName())
                .findFirst()
                .orElseThrow();

        updateKurs.setTeacher(obj.getTeacher());
        updateKurs.setMaxEnrollment(obj.getMaxEnrollment());
        updateKurs.setStudentsEnrolled(obj.getStudentsEnrolled());
        updateKurs.setCredits(obj.getCredits());

        return updateKurs;
    }

}
