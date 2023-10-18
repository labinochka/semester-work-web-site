package ru.kpfu.itis.dao;

import ru.kpfu.itis.dto.PostDto;
import ru.kpfu.itis.model.Beer;
import ru.kpfu.itis.model.Post;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;
import ru.kpfu.itis.service.AccountService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class PostDao {

    private ConnectionProvider connectionProvider;
    private AccountService accountService = new AccountService();

    //language=sql
    final static String SQL_SAVE = "insert into post(author_uuid, date_of_publication, title, content, image) " +
            "values(cast(? as uuid), cast(? as date), ?, ?, ?)";

    //language=sql
    final static String SQL_GET_ALL = "select * from post";

    //language=sql
    final String SQL_GET_BY_UUID = "select * from post where uuid = cast(? as uuid)";

    public PostDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public void save(PostDto post)
            throws DbException {
        try {
            PreparedStatement preparedStatement = this.connectionProvider.getConnection()
                    .prepareStatement(SQL_SAVE);
            String date = String.format("%s-%s-%s", post.date().getYear(), post.date().getMonth(),
                    post.date().getDate());
            preparedStatement.setObject(1, post.author().uuid());
            preparedStatement.setString(2, date);
            preparedStatement.setString(3, post.title());
            preparedStatement.setString(4, post.content());
            preparedStatement.setString(5, post.image());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DbException("Can't save user", e);
        }
    }
    public List<Post> getAll() throws DbException {
        List<Post> posts = new ArrayList<>();
        try {
            PreparedStatement prepStatement = this.connectionProvider.getConnection().prepareStatement(SQL_GET_ALL);
            ResultSet resultPrepSet = prepStatement.executeQuery();
            while (resultPrepSet.next()) {
                posts.add(extract(resultPrepSet));
            }
        } catch (SQLException e) {
            throw new DbException("Can't get post from db.", e);
        }
        return posts;
    }

    public Post getDetail(UUID uuid) throws DbException {
        try {
            PreparedStatement preparedStatement = this.connectionProvider
                    .getConnection()
                    .prepareStatement(SQL_GET_BY_UUID);
            preparedStatement.setString(1, String.valueOf(uuid));
            ResultSet result = preparedStatement.executeQuery();
            boolean hasOne = result.next();
            if (hasOne) {
                return extract(result);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DbException("Can't get post from db.", e);
        }
    }

    public Post extract(ResultSet result) throws DbException {
        Post post;
        try {
            post = new Post((UUID) result.getObject("uuid"),
                    accountService.getById((UUID) result.getObject("author_id")),
                    (Date) result.getObject("date_of_publication"),
                    result.getString("title"),
                    result.getString("content"),
                    result.getString("image"));
            return post;

        } catch (SQLException e) {
            throw new DbException("Can't get post from db.", e);
        }
    }
}
