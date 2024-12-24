package com.borzyshka.test.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class CallRecord {
  String callId;

  String operatorId;
  String countryCode;
  String number;
  Instant startTime;
  Instant endTime;
}


