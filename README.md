# Summarized Calls Report Task

## Prerequisites

The following software should be installed

* [Maven](https://maven.apache.org/download.cgi)
* [Java 17](https://jdk.java.net/)

## User Requirements

### Summary:

Third party service uploads calls information to DB when an operator finishes a call.

````json
{
  "records": [
    {
      "callId": "123457890",
      "operatorId": "123457890",
      "countryCode": "381",
      "number": "123-1122-00",
      "startTime": "2019-10-12T07:20:50.52Z",
      "endTime": "2019-10-12T07:20:50.52Z"
    }
  ]
}
````

As a manager I want to see summarized call report for all calls made during the requested month of the current year

### Acceptance Criteria

1. New endpoint GET /api/v1/call-report?month={month} is added, where month is an integer number from 1 to 12
2. Endpoint produces a 'text/csv' response in the following format

| OperatorId | PhoneNumber    | TotalDuration | Amount |
|------------|----------------|---------------|--------|
| 234234234  | (381)123456789 | 600           | 2      |

3. "|" symbol is used as separator
4. Similar calls are combined into one record
5. Total Duration is calculated in seconds

## Manual Part

Run call-reports application locally:

### Run via jar file

1. open "manual" folder
2. run the following command `java -jar calls-report.jar` with parameters listed below

| Param       | Required | Command Line Format | Description                         | Default value |
|-------------|----------|---------------------|-------------------------------------|---------------|
| server.port | NO       | --server.port       | custom port to start application at | 8080          |

Example

````
java -jar calls-report.jar --server.port=8090
````
3. wait for application to start
4. application will run on the `http://127.0.0.1:{port}` endpoint. Default port is 8080
5. swagger documetation is avaulable at `http://127.0.0.1:{port}/openapi.html`

### Run via docker

1. open "manual" folder
2. build docker image
Example
````
docker run -t calls-report:latest .
````
3. run docker image
````
docker run -p 8080:8080 calls-report:latest
````
3. wait for application to start
4. application will run on the `http://127.0.0.1:{port}` endpoint. Default port is 8080
5. swagger documetation is avaulable at `http://127.0.0.1:{port}/openapi.html`


### Task

1. Groom the story and create a test plan for the functionality
2. Execute tests
3. Create bug reports

## Coding part

Implement ReportCreator#calculateReport method to create a summarized report from list of CallRecord elements based on
the user requirements