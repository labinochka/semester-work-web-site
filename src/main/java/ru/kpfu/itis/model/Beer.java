package ru.kpfu.itis.model;

import java.util.UUID;

public record Beer(
        UUID uuid,
        String sort,
        String content
) {
}
