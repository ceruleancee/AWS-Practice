/*
 * Author: CeruleanCee
 * Created: 04/11/2019
 */

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;

public class S3Bucket {

    // Set credentials here
    BasicAWSCredentials awsCredentials = new BasicAWSCredentials(Credentials.accessKeyId, Credentials.secretAccessKey);

    // Create an s3 bucket
    AmazonS3Client awsS3Client = new AmazonS3Client(awsCredentials);

}
