package ru.kpfu.itis.model;

import ru.kpfu.itis.dto.AccountAuthorDto;

import java.util.Date;
import java.util.UUID;

public record Post(
        UUID uuid,
        AccountAuthorDto author,
        String title,
        String content,
        String image,
        Date date
) {
}
