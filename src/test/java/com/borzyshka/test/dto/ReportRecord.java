package com.borzyshka.test.dto;

import lombok.Data;

@Data
public class ReportRecord {

  private String operatorId;
  private String phoneNumber;
  private long totalDuration;
  private long amount;
}