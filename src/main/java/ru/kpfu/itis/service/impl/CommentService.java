package ru.kpfu.itis.service.impl;

import ru.kpfu.itis.dao.impl.CommentDao;
import ru.kpfu.itis.model.Comment;
import ru.kpfu.itis.service.FullService;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;

import java.util.List;
import java.util.UUID;

public class CommentService implements FullService<Comment> {

    private CommentDao commentDao;

    public CommentService() {
        try {
            commentDao = new CommentDao(ConnectionProvider.getInstance());
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Comment comment) {
        try {
            commentDao.save(comment);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Comment getById(UUID uuid) {
        return null;
    }


    public List<Comment> getAllByPostId(UUID uuid) {
        try {
            return commentDao.getAllByPostId(uuid);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void update(Comment entity) {

    }
}
