package ru.kpfu.itis.service.impl;

import ru.kpfu.itis.model.Comment;
import ru.kpfu.itis.service.FullService;

import java.util.List;
import java.util.UUID;

public class CommentService implements FullService<Comment> {

    @Override
    public void save(Comment entity) {

    }

    @Override
    public Comment getById(UUID uuid) {
        return null;
    }

    @Override
    public List<Comment> getAll() {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void update(Comment entity) {

    }
}
