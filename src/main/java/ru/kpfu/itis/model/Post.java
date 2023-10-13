package ru.kpfu.itis.model;

import java.util.Date;
import java.util.UUID;

public record Post(
        UUID uuid,
        String author,
        String type,
        Date date,
        String title,
        String content
) {
}
