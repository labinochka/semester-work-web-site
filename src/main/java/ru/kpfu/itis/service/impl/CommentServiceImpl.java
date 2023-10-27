package ru.kpfu.itis.service.impl;

import ru.kpfu.itis.dao.CommentDao;
import ru.kpfu.itis.model.Comment;
import ru.kpfu.itis.service.CommentService;
import ru.kpfu.itis.util.DbException;

import java.util.List;
import java.util.UUID;

public class CommentServiceImpl implements CommentService {

    private CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
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

    @Override
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

    @Override
    public List<Comment> getByAuthorAndPost(UUID authorId, UUID postId) {
        try {
            return commentDao.getByAuthorAndPost(authorId, postId);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }
}
