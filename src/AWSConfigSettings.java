/*
 * Author: CeruleanCee
 * Created: 04/11/2019
 */


import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;

public class AWSConfigSettings {

    // Set default credentials
    BasicAWSCredentials awsCredentials = new BasicAWSCredentials(Credentials.accessKey, Credentials.secretKey);

    // Set region
    Regions region = Regions.US_EAST_1;

    // IAM role
    AmazonIdentityManagement iam = AmazonIdentityManagementClientBuilder.defaultClient();


}

