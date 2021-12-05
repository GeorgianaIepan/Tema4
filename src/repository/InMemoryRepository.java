package repository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository<T> implements CrudRepository<T> {

    protected List<T> inMemoryList;

    public InMemoryRepository() {
        this.inMemoryList = new ArrayList<>();
    }

    @Override
    public T create(T obj) {
        this.inMemoryList.add(obj);
        return obj;
    }

    @Override
    public void delete(T obj) {
        this.inMemoryList.remove(obj);
    }

    @Override
    public T getOne(String object) {
        return this.inMemoryList.get(Integer.parseInt(object));
    }

    @Override
    public List<T> getAll() {
        return this.inMemoryList;
    }

    @Override
    public T update(T obj) {
        return this.inMemoryList.get((Integer) obj);
    }
}
