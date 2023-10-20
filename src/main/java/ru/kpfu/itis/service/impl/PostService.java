package ru.kpfu.itis.service.impl;

import ru.kpfu.itis.dao.FullDao;
import ru.kpfu.itis.dao.impl.PostDao;
import ru.kpfu.itis.model.Post;
import ru.kpfu.itis.service.FullService;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;

import java.util.List;
import java.util.UUID;

public class PostService implements FullService<Post> {

    private FullDao<Post> postDao;

    public PostService() {
        try {
            postDao = new PostDao(ConnectionProvider.getInstance());
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Post post) {
        try {
            postDao.save(post);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> getAll() {
        try {
            return postDao.getAll();
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void update(Post entity) {

    }

    @Override
    public Post getById(UUID uuid) {
        try {
            return postDao.getById(uuid);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }
}
