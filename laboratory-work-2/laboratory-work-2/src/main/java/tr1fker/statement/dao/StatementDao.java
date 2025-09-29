package tr1fker.statement.dao;

import java.util.List;
public interface StatementDao<T> {
    void clearTable();
    void insert(T t);
    int getCount();
    List<T> getAll();
    T getById(Long id);
}