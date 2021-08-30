package model;

import java.util.List;

public interface IDAO <T> {
    List<T> getAll();
    T getById(long id);
    void save(T t);
    void update(T t);
    void delete(T t);
}
