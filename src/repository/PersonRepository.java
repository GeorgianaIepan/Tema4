package repository;

import classe.Person;

public class PersonRepository extends InMemoryRepository<Person> {

    public PersonRepository() {
        super();
    }

    @Override
    public Person create(Person obj) {
        return super.create(obj);
    }

    @Override
    public Person update(Person obj) {
        Person updatePerson = this.inMemoryList.stream()
                .filter(person -> person.getFirstName() == obj.getFirstName())
                .findFirst()
                .orElseThrow();

        updatePerson.setLastName(obj.getLastName());

        return updatePerson;
    }
}
