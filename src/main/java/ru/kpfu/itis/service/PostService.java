package ru.kpfu.itis.service;

import ru.kpfu.itis.dao.PostDao;
import ru.kpfu.itis.dto.PostDto;
import ru.kpfu.itis.model.Post;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;

import java.util.List;
import java.util.UUID;

public class PostService {

    private PostDao postDao;

    public PostService() {
        try {
            postDao = new PostDao(ConnectionProvider.getInstance());
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(PostDto post) {
        try {
            postDao.save(post);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Post> getAll() {
        try {
            return postDao.getAll();
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    public Post getDetails(UUID uuid) {
        try {
            return postDao.getDetail(uuid);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }
}
