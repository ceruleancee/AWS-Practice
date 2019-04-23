/*
 * Author: CeruleanCee
 * Created: 04/23/2019
 */

import com.amazonaws.auth.policy.Condition;
import com.amazonaws.auth.policy.Policy;
import com.amazonaws.auth.policy.Principal;
import com.amazonaws.auth.policy.Statement;
import com.amazonaws.auth.policy.actions.S3Actions;
import com.amazonaws.auth.policy.resources.S3ObjectResource;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class Permissions {

    Statement allowPublicReadStatement = new Statement(Statement.Effect.Allow)
            .withPrincipals(Principal.AllUsers)
            .withActions(S3Actions.GetObject)
            .withResources(new S3ObjectResource(mybucketName, "*"));

    Statement allowRestrictedWriteStatement = new Statement(Statement.Effect.Allow)
            .withPrincipals(new Principal("123456"), new Principal("87654321"))
            .withActions(S3Actions.PutObject)
            .withResources(new S3ObjectResource(myBucketName,"*"));

    Policy policy = new Policy()
            .withStatement(allowPublicReadStatement, allowRestrictedWriteStatement);

    AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
    s3.setBuckPolicy(myBucketName, policy.tojson());


}
