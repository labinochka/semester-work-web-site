package ru.kpfu.itis.dao.impl;

import ru.kpfu.itis.dao.AccountDao;
import ru.kpfu.itis.dao.CommentDao;
import ru.kpfu.itis.dao.PostDao;
import ru.kpfu.itis.model.Comment;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CommentDaoImpl implements CommentDao {

    private ConnectionProvider connectionProvider;

    private AccountDao accountDao;
    private PostDao postDao;

    //language=sql
    final String SQL_SAVE = "insert into comment(author_uuid, post_uuid, content, date_of_publication) " +
            "values(cast(? as uuid), cast(? as uuid), ?, cast(? as date))";

    //language=sql
    final String SQL_GET_ALL_BY_POST_ID = "select * from comment where post_uuid = cast(? as uuid)";

    //language=sql
    final String SQL_GET_BY_AUTHOR_AND_POST = "select * from comment where post_uuid = cast(? as uuid) and author_uuid " +
            "= cast(? as uuid)";

    public CommentDaoImpl(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
        accountDao = new AccountDaoImpl(this.connectionProvider);
        postDao = new PostDaoImpl(this.connectionProvider);
    }

    @Override
    public void save(Comment comment) throws DbException {
        try {
            PreparedStatement preparedStatement = this.connectionProvider.getConnection()
                    .prepareStatement(SQL_SAVE);
            String date = String.format("%s-%s-%s", comment.date().getYear() + 1900, comment.date().getMonth() + 1,
                    comment.date().getDate());
            preparedStatement.setObject(1, comment.author().uuid());
            preparedStatement.setObject(2, comment.post().uuid());
            preparedStatement.setString(3, comment.content());
            preparedStatement.setString(4, date);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DbException("Can't save comment", e);
        }
    }

    @Override
    public Comment getById(UUID uuid) throws DbException {
        return null;
    }

    @Override
    public List<Comment> getAllByPostId(UUID uuid) throws DbException {
        List<Comment> comments = new ArrayList<>();
        try {
            PreparedStatement prepStatement = this.connectionProvider.getConnection().prepareStatement(SQL_GET_ALL_BY_POST_ID);
            prepStatement.setObject(1, uuid);
            ResultSet resultPrepSet = prepStatement.executeQuery();
            while (resultPrepSet.next()) {
                comments.add(extract(resultPrepSet));
            }
        } catch (SQLException e) {
            throw new DbException("Can't get comment from db.", e);
        }
        return comments;
    }

    @Override
    public void delete(UUID id) throws DbException {

    }

    @Override
    public void update(Comment entity) throws DbException {

    }

    @Override
    public List<Comment> getByAuthorAndPost(UUID authorId, UUID postId) throws DbException {
        List<Comment> comments = new ArrayList<>();
        try {
            PreparedStatement prepStatement = this.connectionProvider.getConnection().prepareStatement(SQL_GET_BY_AUTHOR_AND_POST);
            prepStatement.setObject(1, postId);
            prepStatement.setObject(2, authorId);
            ResultSet resultPrepSet = prepStatement.executeQuery();
            while (resultPrepSet.next()) {
                comments.add(extract(resultPrepSet));
            }
        } catch (SQLException e) {
            throw new DbException("Can't get comment from db.", e);
        }
        return comments;
    }

    @Override
    public Comment extract(ResultSet result) throws DbException {
        Comment comment;
        try {
            comment = new Comment((UUID) result.getObject("uuid"),
                    accountDao.getById((UUID) result.getObject("author_uuid")),
                    postDao.getById((UUID) result.getObject("post_uuid")),
                    result.getString("content"),
                    (Date) result.getObject("date_of_publication"));
            return comment;

        } catch (SQLException e) {
            throw new DbException("Can't get post from db.", e);
        }
    }
}
