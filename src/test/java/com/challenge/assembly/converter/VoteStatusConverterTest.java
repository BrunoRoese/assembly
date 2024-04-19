package com.challenge.assembly.converter;

import com.challenge.assembly.api.converter.VoteStatusConverter;
import com.challenge.assembly.api.domain.VoteStatus;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class VoteStatusConverterTest {

    @InjectMocks
    private VoteStatusConverter voteStatusConverter;

    @Nested
    class ConvertToDatabaseColumn {
        @Test
        void shouldConvertToDatabaseColumn() {
            var response = voteStatusConverter.convertToDatabaseColumn(VoteStatus.YES);

            assertThat(response).isEqualTo("yes");
        }

        @Test
        void shouldReturnNullIfVoteStatusIsNull() {
            var response = voteStatusConverter.convertToDatabaseColumn(null);

            assertThat(response).isNull();
        }
    }

    @Nested
    class ConvertToEntityAttribute {
        @ParameterizedTest
        @ValueSource(strings = {"yes", "no"})
        void shouldConvertToEntityAttribute(String status) {
            var response = voteStatusConverter.convertToEntityAttribute(status);

            assertThat(response).isEqualTo(VoteStatus.valueOf(status.toUpperCase()));
        }

        @ParameterizedTest
        @NullAndEmptySource
        void shouldReturnNullIfStatusIsNullOrEmpty(String status) {
            var response = voteStatusConverter.convertToEntityAttribute(status);

            assertThat(response).isNull();
        }

        @Test
        void shouldThrowIllegalArgumentExceptionIfStatusDoesntExist() {
            assertThrows(IllegalArgumentException.class, () -> voteStatusConverter.convertToEntityAttribute("invalid"));
        }
    }
}
