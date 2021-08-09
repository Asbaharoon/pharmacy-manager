package model;

import java.util.List;

public interface DAO<T> {
    List<T> getAll();
    void save(T t);
    void delete(int id);
    void update(T t);
}
