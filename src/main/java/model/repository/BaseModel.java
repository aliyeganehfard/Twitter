package model.repository;

import java.util.List;

public interface BaseModel<E> {
    Integer save(E e);
    void update(E e);
    void delete(Integer id);
    E findById(Integer id);
    List<E> findAll();
}
