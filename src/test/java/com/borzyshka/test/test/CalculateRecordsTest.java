package com.borzyshka.test.test;

import com.borzyshka.test.dto.CallRecord;
import com.borzyshka.test.dto.ReportRecord;
import com.borzyshka.test.service.JsonParser;
import com.borzyshka.test.service.ReportCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CalculateRecordsTest extends AbstractTestNGSpringContextTests {

  @Autowired
  protected ReportCreator reportCreator;
  @Autowired
  protected JsonParser jsonParser;

  @DataProvider(name = "callDataProvider")
  public Object[][] callDataProvider() {
    return new Object[][]{
            //
            new Object[]{
                    jsonParser.parseFileAsList(CallRecord.class, "data/test1_input.json"),
                    jsonParser.parseFileAsList(ReportRecord.class, "data/test1_expected.json")
            },
            //
            new Object[]{
                    jsonParser.parseFileAsList(CallRecord.class, "data/test2_input.json"),
                    jsonParser.parseFileAsList(ReportRecord.class, "data/test2_expected.json")
            },
            //
            new Object[]{
                    jsonParser.parseFileAsList(CallRecord.class, "data/test3_input.json"),
                    jsonParser.parseFileAsList(ReportRecord.class, "data/test3_expected.json")
            }
    };
  }

  @Test(dataProvider = "callDataProvider")
  public void testCalculateReport(List<CallRecord> input, List<ReportRecord> expected) {
    //
    List<ReportRecord> actual = reportCreator.calculateReport(input);
    //
    assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
  }
}
