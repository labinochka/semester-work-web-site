package ru.kpfu.itis.dao;

import ru.kpfu.itis.model.Comment;
import ru.kpfu.itis.model.Post;
import ru.kpfu.itis.util.DbException;

import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

public interface CommentDao {

    void save(Comment comment) throws DbException;

    Comment getById(UUID uuid) throws DbException;

    List<Comment> getAllByPostId(UUID uuid) throws DbException;

    void delete(UUID id) throws DbException;

    void update(Comment comment) throws DbException;

    List<Comment> getByAuthorAndPost(UUID authorId, UUID postId) throws DbException;

    Comment extract(ResultSet result) throws DbException;
}
