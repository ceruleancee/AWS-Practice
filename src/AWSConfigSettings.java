/*
 * Author: CeruleanCee
 * Created: 04/11/2019
 */


import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;

public class AWSConfigSettings {

    // CONFIGURATION
    // Set credentials
    AwsBasicCredentials basicCredentials = AwsBasicCredentials.create(UserCredentials.accessKey, UserCredentials.secretKey);

    // Set Region
    Region region = Region.US_EAST_1;


}

