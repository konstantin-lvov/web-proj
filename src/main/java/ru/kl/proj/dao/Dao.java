package ru.kl.proj.dao;

import ru.kl.proj.entity.Organization;

import java.util.List;

public interface Dao<T> {

    List<T> getAll();
    void create(T entity);
    T read(int entityProp);
    void update(T entity);
    void delete(int entityProp);
}
