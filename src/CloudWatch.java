/*
 * Author: CeruleanCee
 * Created: 04/23/2019
 */

import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.cloudwatch.model.ListMetricsRequest;
import com.amazonaws.services.cloudwatch.model.ListMetricsResult;
import com.amazonaws.services.cloudwatch.model.Metric;

public class CloudWatch {

    AmazonCloudwatch cw = AmazonCloudWatchClientBuilder.defaultClient();

    ListMetricsRequest request = new ListMetricRequest()
            .withMetricName(name)
            .withNamespace(namespace);


}
