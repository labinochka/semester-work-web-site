package ru.kpfu.itis.service.impl;

import ru.kpfu.itis.dao.CommentDao;
import ru.kpfu.itis.dto.CommentEditDto;
import ru.kpfu.itis.dto.CommentUpdateDto;
import ru.kpfu.itis.model.Account;
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
    public CommentEditDto getById(UUID uuid) {
        try {
            return commentDao.getById(uuid);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CommentEditDto> getAllByPostId(UUID uuid, Account currentAccount) {
        try {
            List<CommentEditDto> comments = commentDao.getAllByPostId(uuid);
            for (CommentEditDto comment : comments) {
                if (currentAccount != null && comment.getAuthor().uuid().equals(currentAccount.uuid())) {
                    comment.setEdit(true);
                }
            }
            return comments;
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CommentEditDto> getAllByPostId(UUID uuid) {
        try {
            return commentDao.getAllByPostId(uuid);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(CommentEditDto comment) {
        try {
            commentDao.delete(comment);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(CommentUpdateDto comment) {
        try {
            commentDao.update(comment);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

}
