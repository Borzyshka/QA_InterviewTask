package com.borzyshka.test.dto;

import lombok.Builder;

@Builder
public record ReportRecord(String operatorId,
                           String phoneNumber,
                           long totalDuration,
                           long amount) {
}