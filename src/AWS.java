/*
 * Author: CeruleanCee
 * Created: 04/11/2019
 */


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class AWS {

    // Set default credentials
    DefaultAWSCredentialsProviderChain chain = new DefaultAWSCredentialsProviderChain();
    AWSCredentials cred = chain.getCredentials();

    // Set region
    Regions region = Regions.US_EAST_1;
    //ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider("myProfile");

    // Create Amazon DynamoDB client builder
    AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder.standard();

    AmazonDynamoDB dynamoDB =
            dynamoDB = AmazonDynamoDBClientBuilder.standard()
                    .withRegion(region)
                    .withCredentials(chain)
                    .build();

}

