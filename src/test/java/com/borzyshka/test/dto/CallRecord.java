package com.borzyshka.test.dto;

import lombok.Builder;
import lombok.With;

import java.time.Instant;

@With
@Builder
public record CallRecord(String callId,
                         String operatorId,
                         String countryCode,
                         String number,
                         Instant startTime,
                         Instant endTime) {
}
