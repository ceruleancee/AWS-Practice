/*
 * Author: CeruleanCee
 * Created: 04/23/2019
 */

import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatchlogs.CloudWatchLogsClient;
import software.amazon.awssdk.services.cloudwatchlogs.model.GetLogEventsRequest;

public class CloudWatchPolling {

    public CloudWatchLogsClient logsClient;

    // Create a CloudwatchLog client
    public CloudWatchLogsClient createCloudWatchLogClient(AwsCredentials basicCredentials, Region region) {

        this.logsClient = CloudWatchLogsClient.builder()
                // TODO resolve credentialProvider value with static values
                .credentialsProvider(StaticCredentialsProvider.create(basicCredentials))
                .region(region)
                .build();

        return logsClient;
    }

    public CloudWatchLogsClient pullLog(String logGroupName, String logStreamName){
        this.logsClient.getLogEvents(GetLogEventsRequest.builder()
                                                        .logGroupName(logGroupName)
                                                        .logStreamName(logStreamName)
                                                        .startFromHead(true)
                                                        .build());
        // TODO not sure about this return
        return logsClient;

    }

    public GetLogEventsRequest createLogEvent( String logGroupName, String logStreamName, boolean fromStart){
        GetLogEventsRequest logEventObject = GetLogEventsRequest.builder()
                .logGroupName(logGroupName)
                .logStreamName(logStreamName)
                //.startTime()
                //.endTime()
                //.nextToken(nextToken)
                //.limit(logLimit)
                .startFromHead(fromStart)
                .build();

        return logEventObject;
    }



}
