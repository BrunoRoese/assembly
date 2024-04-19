package com.challenge.assembly.utils;

import com.challenge.assembly.api.exception.ConflictException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.challenge.assembly.api.utils.UuidUtils.convertUuid;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UuidUtilsTest {

    @ParameterizedTest
    @ValueSource(strings = {"test", "", " ", "  "})
    void shouldReturnFalseWhenUuidIsInvalid(String uuid) {
        assertThrows(ConflictException.class, () -> convertUuid(uuid));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000", "123e4567-e89b-12d3-a456-426614174001"})
    void shouldReturnTrueWhenUuidIsValid(String uuid) {
       assertDoesNotThrow(() -> convertUuid(uuid));
    }
}
