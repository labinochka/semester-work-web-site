package ru.kpfu.itis.service;

import ru.kpfu.itis.util.DbException;

import java.util.List;
import java.util.UUID;

public interface FullService<T> {

    void save(T entity);
    T getById(UUID uuid);
    void delete(UUID id);
    void update(T entity);
}
