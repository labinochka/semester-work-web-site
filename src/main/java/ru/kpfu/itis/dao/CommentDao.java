package ru.kpfu.itis.dao;

import ru.kpfu.itis.dto.CommentEditDto;
import ru.kpfu.itis.model.Comment;
import ru.kpfu.itis.util.DbException;

import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

public interface CommentDao {

    void save(Comment comment) throws DbException;

    Comment getById(UUID uuid) throws DbException;

    List<CommentEditDto> getAllByPostId(UUID uuid) throws DbException;

    void delete(UUID id) throws DbException;

    void update(Comment comment) throws DbException;

    CommentEditDto extract(ResultSet result) throws DbException;
}
