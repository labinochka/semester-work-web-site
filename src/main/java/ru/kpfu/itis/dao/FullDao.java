package ru.kpfu.itis.dao;

import ru.kpfu.itis.model.Post;
import ru.kpfu.itis.util.DbException;

import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

public interface FullDao<T> {
    void save(T entity) throws DbException;
    T getById(UUID uuid) throws DbException;
    void delete(UUID id) throws DbException;
    void update(T entity) throws DbException;
    T extract(ResultSet result) throws DbException;
}
