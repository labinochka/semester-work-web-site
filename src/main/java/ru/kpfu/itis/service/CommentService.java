package ru.kpfu.itis.service;


import ru.kpfu.itis.dto.CommentEditDto;
import ru.kpfu.itis.dto.CommentUpdateDto;
import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.model.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    void save(Comment comment);

    CommentEditDto getById(UUID uuid);

    List<CommentEditDto> getAllByPostId(UUID uuid, Account currentAccount);

    List<CommentEditDto> getAllByPostId(UUID uuid);

    void delete(CommentEditDto comment);

    void update(CommentUpdateDto comment);

}
