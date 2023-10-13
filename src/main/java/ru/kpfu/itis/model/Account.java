package ru.kpfu.itis.model;

import java.util.UUID;

public record Account(
        UUID uuid,
        String username,
        String name,
        String lastname,
        String email,
        String password
) {
}