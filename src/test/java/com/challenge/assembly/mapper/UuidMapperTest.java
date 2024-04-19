package com.challenge.assembly.mapper;

import com.challenge.assembly.api.exception.ConflictException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.challenge.assembly.api.mapper.UuidMapper.mapStringToUuid;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UuidMapperTest {

    @ParameterizedTest
    @ValueSource(strings = {"test", "", " ", "  "})
    void shouldReturnFalseWhenUuidIsInvalid(String uuid) {
        assertThrows(ConflictException.class, () -> mapStringToUuid(uuid));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000", "123e4567-e89b-12d3-a456-426614174001"})
    void shouldReturnTrueWhenUuidIsValid(String uuid) {
       assertDoesNotThrow(() -> mapStringToUuid(uuid));
    }
}
