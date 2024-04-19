package com.challenge.assembly.api.mapper;

import com.challenge.assembly.api.exception.ConflictException;

import java.util.UUID;

public class UuidMapper {

    public static UUID mapStringToUuid(String uuid) {
        try {
            return UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new ConflictException("Invalid UUID");
        }
    }
}
