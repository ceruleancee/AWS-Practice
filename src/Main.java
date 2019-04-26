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

        // Pull log from the beginning from the designated logGroup and logStream
        logsClient.getLogEvents(GetLogEventsRequest.builder()
                                        .logGroupName(logGroupName)
                                        .logStreamName(logStreamName)
                                        .startFromHead(true)
                                        .build());

        // Print the log
        System.out.println(logsClient.getLogEvents(GetLogEventsRequest.builder()
                                                           .logGroupName(logGroupName)
                                                           .logStreamName(logStreamName)
                                                           .build()));

    }
}
