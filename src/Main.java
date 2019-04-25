import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatchlogs.CloudWatchLogsClient;
import software.amazon.awssdk.services.cloudwatchlogs.model.GetLogEventsRequest;


public class Main {
    public static void main(String[] args) {

        String logGroupName = "graylogs";
        String logStreamName = "graylog";
        String awsUser = "cee";

        // Set credentials
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(Credentials.accessKey, Credentials.secretKey);
        DefaultAWSCredentialsProviderChain awsCredentialsProviderChain = DefaultAWSCredentialsProviderChain.getInstance();
        AWSStaticCredentialsProvider awsStaticCredentialsProvider;

        // Set Region
        Region region = Region.US_EAST_1;

        // Create a CloudwatchLog client
        CloudWatchLogsClient logsClient = CloudWatchLogsClient.builder()
                // TODO resolve credentialProvider value with static values
                //.credentialsProvider()
                .region(region)
                .build();

        logsClient.getLogEvents(GetLogEventsRequest.builder()
                                        .logGroupName(logGroupName)
                                        .logStreamName(logStreamName)
                                        .build());

        System.out.println(logsClient);

        //AmazonCloudWatchClientBuilder.defaultClient().listMetrics();

    }
}
