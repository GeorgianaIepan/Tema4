package repository;

import java.io.IOException;
import java.util.List;

public interface CrudRepository<T> {

    T create(T obj) throws IOException;

    void delete(T obj) throws IOException;

    T getOne(String obj) throws IOException, ClassNotFoundException;

    List<T> getAll() throws IOException, ClassNotFoundException;

    T update(T obj) throws IOException, ClassNotFoundException;


}
