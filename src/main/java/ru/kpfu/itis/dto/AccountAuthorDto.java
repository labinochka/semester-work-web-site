package ru.kpfu.itis.dto;

import java.util.UUID;

public record AccountAuthorDto(
        UUID uuid,
        String username
) {
}
