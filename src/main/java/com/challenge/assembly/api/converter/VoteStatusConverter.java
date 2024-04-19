package com.challenge.assembly.api.converter;

import com.challenge.assembly.api.domain.VoteStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class VoteStatusConverter implements AttributeConverter<VoteStatus, String> {

    @Override
    public String convertToDatabaseColumn(VoteStatus voteStatus) {
        if (voteStatus == null) {
            return null;
        }

        return voteStatus.getStatus();
    }

    @Override
    public VoteStatus convertToEntityAttribute(String status) {
        if (status == null || status.isEmpty()) {
            return null;
        }

        return Stream.of(VoteStatus.values())
                .filter(s -> s.getStatus().equals(status))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
