package ru.kpfu.itis.dto;

import ru.kpfu.itis.model.Account;

import java.util.Date;

public record PostDto(
        Account author,
        Date date,
        String title,
        String content,
        String image
) {
}
