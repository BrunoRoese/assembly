package com.challenge.assembly.api.utils;

import com.challenge.assembly.api.exception.ConflictException;

import java.util.UUID;

public class UuidUtils {

    public static UUID convertUuid(String uuid) {
        try {
            return UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new ConflictException("Invalid UUID");
        }
    }
}
