/*
 * Author: CeruleanCee
 * Created: 04/26/2019
 */

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatchlogs.CloudWatchLogsClient;
import software.amazon.awssdk.services.cloudwatchlogs.model.GetLogEventsRequest;


public class Main {
    public static void main(String[] args) {

        String logGroupName = "graylogs";
        String logStreamName = "graylog";
        int logLimit;
        int logIndex;

        // CONFIGURATION
        // Set credentials
        AwsBasicCredentials basicCredentials = AwsBasicCredentials.create(UserCredentials.accessKey, UserCredentials.secretKey);

        // Set Region
        Region region = Region.US_EAST_1;

        // CLOUDWATCH
        // Create a CloudwatchLog client
        CloudWatchLogsClient logsClient = CloudWatchLogsClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(basicCredentials))
                .region(region)
                .build();

        // Create GetLogEventRequest object
        GetLogEventsRequest logEventObject = GetLogEventsRequest.builder()
                .logGroupName(logGroupName)
                .logStreamName(logStreamName)
                //.startTime()
                //.endTime()
                //.nextToken()
                //.limit(logLimit)
                .startFromHead(true)
                .build();


        logLimit = logsClient.getLogEvents(logEventObject).events().size();

        // Designate logEventObject from the START
        logsClient.getLogEvents(logEventObject);
        System.out.print("\n Pull log from the START, and print to console.\n\n");

        // Print first and last log
        System.out.print(logsClient.getLogEvents(logEventObject).events().get(0));
        System.out.print(logsClient.getLogEvents(logEventObject).events().get(logLimit - 1));

        // Iterate through all the events
        for (int i = 0; i < logLimit; i++) {
            System.out.print(logsClient.getLogEvents(logEventObject).events().get(i) + "\n");

        }

        // Get the total number of the current logEvents
        logLimit = logsClient.getLogEvents(logEventObject).events().size();

        // Set next token
        String nextToken = logsClient.getLogEvents(logEventObject).nextForwardToken();

        // Create new GetLogEventRequest object
        GetLogEventsRequest logEventObject02 = GetLogEventsRequest.builder()
                .logGroupName(logGroupName)
                .logStreamName(logStreamName)
                //.startTime()
                //.endTime()
                .nextToken(nextToken)
                .limit(logLimit)
                .startFromHead(false)
                .build();

//        // Designate logEventObject from LAST REQUEST
        // TODO resolve why this isnt working
        logsClient.getLogEvents(logEventObject02);


    }
}
