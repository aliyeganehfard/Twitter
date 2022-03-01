package controller.service;

import model.repository.AccountRepository;
import model.repository.BaseModel;

import java.util.List;

public abstract class BaseService<E, R extends BaseModel<E>> implements BaseModel<E> {
    R r;

    public BaseService(R r) {
        this.r = r;
    }

    @Override
    public Integer save(E e) {
        return r.save(e);
    }

    @Override
    public void update(E e) {
        r.update(e);
    }

    @Override
    public void delete(Integer id) {
        r.delete(id);
    }

    @Override
    public E findById(Integer id) {
        return r.findById(id);
    }

    @Override
    public List<E> findAll() {
        return r.findAll();
    }
}
