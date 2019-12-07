package com.bushemi.services;

import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.util.Objects.isNull;

@Service
public class UuidStringConverter {
    public UUID fromString(String text) {
        if (isNull(text)) return null;
        return UUID.fromString(text);
    }

    public String fromUUID(UUID id) {
        if (isNull(id)) return null;
        return id.toString();
    }
}
