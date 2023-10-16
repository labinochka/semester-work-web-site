package ru.kpfu.itis.dto;

import java.util.Date;

public record AccountDto(
        String username,
        String name,
        String lastname,
        Date birthday,
        String email,
        String password
) {
}
