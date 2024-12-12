package com.borzyshka.test.test;

import com.borzyshka.test.service.ReportCreator;
import com.borzyshka.test.dto.CallRecord;
import com.borzyshka.test.dto.ReportRecord;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CalculateRecordsTest extends AbstractTestNGSpringContextTests {

  @Autowired
  protected ReportCreator reportCreator;

  @Test
  public void testCombineRecords() {
    //
    final Instant now = Instant.now();
    List<CallRecord> records = List.of(
            CallRecord.builder()
                    .callId(Faker.instance().number().digits(10))
                    .operatorId("1234567890")
                    .countryCode("381")
                    .number("123456785")
                    .startTime(now.minus(10, ChronoUnit.MINUTES))
                    .endTime(now)
                    .build(),
            CallRecord.builder()
                    .callId(Faker.instance().number().digits(10))
                    .operatorId("1234567890")
                    .countryCode("381")
                    .number("123456785")
                    .startTime(now.minus(25, ChronoUnit.MINUTES))
                    .endTime(now.minus(9, ChronoUnit.MINUTES))
                    .build(),
            CallRecord.builder()
                    .callId(Faker.instance().number().digits(10))
                    .operatorId("0123456789")
                    .countryCode("381")
                    .number("123456785")
                    .startTime(now.minus(10, ChronoUnit.MINUTES))
                    .endTime(now)
                    .build()
    );
    //
    List<ReportRecord> expected = List.of(
            ReportRecord.builder().operatorId("1234567890").phoneNumber("(381)123456785")
                    .amount(2L).totalDuration(1560L).build(),
            ReportRecord.builder().operatorId("0123456789").phoneNumber("(381)123456785")
                    .amount(1L).totalDuration(600L).build()
    );
    //
    List<ReportRecord> actual = reportCreator.calculateReport(records);
    assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
  }
}
