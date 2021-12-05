package repository;

import classe.Lehrer;

public class LehrerRepository extends InMemoryRepository<Lehrer> {

    public LehrerRepository() {
        super();
    }

    @Override
    public Lehrer update(Lehrer obj) {
        Lehrer updateLehrer = this.inMemoryList.stream()
                .filter(lehrer -> lehrer.getFirstName() == obj.getFirstName())
                .findFirst()
                .orElseThrow();

        updateLehrer.setLastName(obj.getLastName());
        updateLehrer.setCourses(obj.getCourses());

        return updateLehrer;
    }
}
