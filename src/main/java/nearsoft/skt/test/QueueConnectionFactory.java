package nearsoft.skt.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by rfonseca on 6/15/17.
 */
@Component
public class QueueConnectionFactory {

    @Autowired
    private QueueConfigurationBean queueConfigurationBean;
    private Connection connection;
    private Channel channel;

    public Channel getChannel() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(queueConfigurationBean.getHost());
        factory.setPort(Integer.valueOf(queueConfigurationBean.getPort()));
        connection = factory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(queueConfigurationBean.getQueueName(), false, false, false, null);
        return channel;
    }

    public void close() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }

    public String getQueueName(){
        return queueConfigurationBean.getQueueName();
    }
}