/*
 * Author: CeruleanCee
 * Created: 04/11/2019
 */

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient;

public class AWS {

    // Select a region and create a client
    Ec2Client ec2 = Ec2Client.builder()
            .region(Region.US_EAST_1)
            //.endpointOverride(URI.create("https://ec2.us-west-1.amazonaws.com"))
            .build();


    // Create an HTTP connection
    KinesisAsyncClient client = KinesisAsyncClient.builder()
//            .httpClientBuilder(NettyNioAsyncHttpClient.builder()
//                                       .maxConcurrency(100)
//                                       .maxPendingConnectionAcquires(10_000))
            .build();
}

