package ru.kpfu.itis.service;


import ru.kpfu.itis.dto.CommentEditDto;
import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.model.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    void save(Comment comment);

    Comment getById(UUID uuid);

    List<CommentEditDto> getAllByPostId(UUID uuid, Account currentAccount);

    void delete(UUID id);

    void update(Comment entity);

}
