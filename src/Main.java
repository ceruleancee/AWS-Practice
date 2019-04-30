/*
 * Author: CeruleanCee
 * Created: 04/26/2019
 */

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatchlogs.CloudWatchLogsClient;
import software.amazon.awssdk.services.cloudwatchlogs.model.DescribeLogStreamsRequest;
import software.amazon.awssdk.services.cloudwatchlogs.model.GetLogEventsRequest;


public class Main {
    public static void main(String[] args) {

        String logGroupName = "";
        String logStreamName = "";
        int logLimit;
        int logGroupListSize;
        int logStreamListSize;

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

        // Number of logGroupNames that exist
        logGroupListSize = logsClient.describeLogGroups().logGroups().size();

        // List all the logGroupName(s) available
        for (int i = 0; i < logGroupListSize; i++) {

            logGroupName = logsClient.describeLogGroups().logGroups().get(i).logGroupName();
            DescribeLogStreamsRequest logStreamsRequest = DescribeLogStreamsRequest.builder()
                    .logGroupName(logGroupName)
                    .build();

            // Number of logStreamNames that exist
            logStreamListSize = logsClient.describeLogStreams(((logStreamsRequest))).logStreams().size();
            System.out.print("Log Group Name: " + logGroupName + "\n");

            // List all logStream(s) available
            for (int j = 0; j < logStreamListSize; j++) {

                logStreamName = logsClient.describeLogStreams(((logStreamsRequest))).logStreams().get(j).logStreamName();
                System.out.print("     Log Stream Name: " + logStreamName + "\n");
            }
        }

        // Hardcoded variables for test purposes
        logGroupName = "graylogs";
        logStreamName = "graylog";

        // Create GetLogEventRequest object
        GetLogEventsRequest logEventObject = GetLogEventsRequest.builder()
                .logGroupName(logGroupName)
                .logStreamName(logStreamName) // logStreamName is required
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

        //Iterate through all the events
        for (int i = 0; i < logLimit; i++) {
            System.out.print(" [" + i + "] " + logsClient.getLogEvents(logEventObject).events().get(i) + "\n");
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
                //.limit(logLimit)
                .startFromHead(false)
                .build();

        // TODO resolve nextToken issue
        // Designate logEventObject from LAST REQUEST
        //logsClient.getLogEvents(logEventObject02);


    }
}
