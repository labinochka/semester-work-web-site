package ru.kpfu.itis.dao.impl;

import ru.kpfu.itis.dao.FullDao;
import ru.kpfu.itis.model.Comment;
import ru.kpfu.itis.util.DbException;

import java.util.List;
import java.util.UUID;

public class CommentDao implements FullDao<Comment> {

    @Override
    public void save(Comment entity) throws DbException {

    }

    @Override
    public Comment getById(UUID uuid) throws DbException {
        return null;
    }

    @Override
    public List<Comment> getAll() throws DbException {
        return null;
    }

    @Override
    public void delete(UUID id) throws DbException {

    }

    @Override
    public void update(Comment entity) throws DbException {

    }
}
