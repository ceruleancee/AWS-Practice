
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.CreateAccessKeyRequest;
import com.amazonaws.services.identitymanagement.model.CreateAccessKeyResult;

public class IAM {

    AmazonIdentityManagement iam = AmazonIdentityManagementClientBuilder.defaultClient();

    CreateAccessKeyRequest request = new CreateAccessKeyRequest()
            .withUserName(cee);

    CreateAccessKeyResult response = iam.createAccessKey(request);

}
