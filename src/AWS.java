/*
 * Author: CeruleanCee
 * Created: 04/11/2019
 */


import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class AWS {

    // Set default credentials
    BasicAWSCredentials awsCredentials = new BasicAWSCredentials(Credentials.accessKeyId, Credentials.secretAccessKey);

    // Set region
    Regions region = Regions.US_EAST_1;

    // Check role permissions

    // Pull log


    ClientConfiguration configuration = new ClientConfiguration();


    // Create a NoSQL database
    AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder.standard();

}

