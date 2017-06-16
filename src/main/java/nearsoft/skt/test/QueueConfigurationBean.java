package nearsoft.skt.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Configuration bean
 */
@Component
public class QueueConfigurationBean {

    @Value("${config.queueName}")
    private String queueName;

    @Value("${config.host}")
    private String host;

    @Value("${config.queuePort}")
    private String port; // default port


    public String getQueueName() {
        return queueName;
    }

    public String getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }
}