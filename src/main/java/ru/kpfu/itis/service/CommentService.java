package ru.kpfu.itis.service;


import ru.kpfu.itis.model.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    void save(Comment comment);

    Comment getById(UUID uuid);

    List<Comment> getAllByPostId(UUID uuid);

    void delete(UUID id);

    void update(Comment entity);

    List<Comment> getByAuthorAndPost(UUID authorId, UUID postId);
}
