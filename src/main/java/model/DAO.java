package model;

import java.util.List;

public interface DAO<T> {
    List<T> getAll();
    T searchById(long id);
    void insert(T t);
    void update(T t);
    void delete(T t);
}
