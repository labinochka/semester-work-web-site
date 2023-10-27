package ru.kpfu.itis.dto;

import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.model.Post;

import java.util.Date;
import java.util.UUID;

public class CommentEditDto {

    private UUID uuid;
    private Account author;
    private Post post;
    private String content;
    private Date date;
    private boolean isEdit = false;

    public CommentEditDto(UUID uuid, Account author, Post post, String content, Date date, boolean isEdit) {
        this.uuid = uuid;
        this.author = author;
        this.post = post;
        this.content = content;
        this.date = date;
        this.isEdit = isEdit;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Account getAuthor() {
        return author;
    }

    public Post getPost() {
        return post;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }
}
