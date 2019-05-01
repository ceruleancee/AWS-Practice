import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatchlogs.CloudWatchLogsClient;
import software.amazon.awssdk.services.cloudwatchlogs.model.DescribeLogStreamsRequest;
import software.amazon.awssdk.services.cloudwatchlogs.model.GetLogEventsRequest;
import software.amazon.awssdk.services.cloudwatchlogs.model.GetLogEventsResponse;


public class Main {
    public static void main(String[] args) {

        String logGroupName;
        String logStreamName;
        int logLimit = 1000;
        int logGroupListSize;
        int logStreamListSize;
        boolean reachedEnd = false;
        String forwardToken;

        // CONFIGURATION
        // Set credentials
        AwsBasicCredentials basicCredentials =
                AwsBasicCredentials.create(UserCredentials.accessKey, UserCredentials.secretKey);

        // Set Region
        Region region = Region.US_EAST_1;

        // CLOUDWATCH
        // Create a CloudwatchLog client
        CloudWatchLogsClient cloudWatchLogsClient = CloudWatchLogsClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(basicCredentials))
                .region(region)
                .build();

        // Number of logGroupNames that exist
        logGroupListSize = cloudWatchLogsClient.describeLogGroups().logGroups().size();

        // List all the logGroupName(s) available
        for (int i = 0; i < logGroupListSize; i++) {
            logGroupName = cloudWatchLogsClient.describeLogGroups().logGroups().get(i).logGroupName();
            DescribeLogStreamsRequest logStreamsRequest = DescribeLogStreamsRequest.builder()
                    .logGroupName(logGroupName)
                    .build();

            // Number of logStreamNames that exist
            logStreamListSize = cloudWatchLogsClient.describeLogStreams(((logStreamsRequest))).logStreams().size();
            System.out.print("Log Group Name: " + logGroupName + "\n");

            // List all logStream(s) available
            for (int j = 0; j < logStreamListSize; j++) {
                logStreamName = cloudWatchLogsClient.describeLogStreams(((logStreamsRequest))).logStreams().get(j).logStreamName();
                System.out.print("     Log Stream Name: " + logStreamName + "\n");
            }
        }

        // Hardcoded variables for test purposes
        logGroupName = "graylogs";
        logStreamName = "graylog";

        // Create GetLogEventRequest object to START
        GetLogEventsRequest getLogEventsRequest = GetLogEventsRequest.builder()
                .logGroupName(logGroupName)
                .logStreamName(logStreamName) // logStreamName is required
                //.startTime()
                //.endTime()
                //.nextToken()
                .limit(logLimit)
                .startFromHead(true)
                .build();

        // Create GetLogEventRequest response to PROGRESS FORWARD
        GetLogEventsResponse logResponse = cloudWatchLogsClient.getLogEvents(getLogEventsRequest);
        logLimit = 5;//logResponse.events().size();
        forwardToken = logResponse.nextForwardToken();
        int messageIndex = 0;

        // TODO Find out if it reaches the end, and when
        // Read all the messages
        while (!reachedEnd) {

            // Print message to console
            for (int i = 0; i < logLimit; i++) {
                System.out.print(" [" + messageIndex + "] " +
                                         cloudWatchLogsClient.getLogEvents(getLogEventsRequest).events().get(i)
                                         + "\n");
                messageIndex++;
            }

            // If number of log messages received equals zero
            reachedEnd = true;

            // Reassign GetLogEventResponse with forwardToken to PROGRESS FORWARD
            getLogEventsRequest = GetLogEventsRequest.builder()
                    .logGroupName(logGroupName)
                    .logStreamName(logStreamName) // logStreamName is required
                    .nextToken(forwardToken)
                    .limit(1000)
                    .build();

            logResponse = cloudWatchLogsClient.getLogEvents(getLogEventsRequest);

        }

//        // Designate getLogEventsRequest from the START
//        cloudWatchLogsClient.getLogEvents(getLogEventsRequest);
//        //System.out.print("\n Pull log from the START, and print to console.\n\n");
//
        //Iterate through all the events
        for (int i = 0; i < logLimit; i++) {
            System.out.print(" [" + i + "] " + cloudWatchLogsClient.getLogEvents(getLogEventsRequest).events().get(i) + "\n");
        }
//
//        // Set next token?
//
//        String token  = cloudWatchLogsClient.getLogEvents(getLogEventsRequest).nextForwardToken();
//        System.out.print(token);
//        GetLogEventsRequest anotherLogEventRequest = GetLogEventsRequest.builder().logGroupName(logGroupName).logStreamName(logStreamName).nextToken(nextToken).startFromHead(false).build();
//        logLimit = cloudWatchLogsClient.getLogEvents(anotherLogEventRequest).events().size();
//        cloudWatchLogsClient.getLogEvents(anotherLogEventRequest);
//        //cloudWatchLogsClient.getLogEvents(getLogEventsRequest);
//        for (int i = 0; i < logLimit; i++) {
//            //System.out.print(" [" + i + "] " + cloudWatchLogsClient.getLogEvents(anotherLogEventRequest).events().get(i) + "\n");
//        }
//
////        GetLogEventsRequest anotherOne = anotherLogEventsRequest(logGroupName, logStreamName, nextToken);
////        cloudWatchLogsClient.getLogEvents(anotherOne);
//
//        cloudWatchLogsClient.getLogEvents();


            // TODO resolve nextToken issue
            // Designate getLogEventsRequest from LAST REQUEST
            //cloudWatchLogsClient.getLogEvents(getLogEventsRequest.nextToken(nextToken);


        }



    }
