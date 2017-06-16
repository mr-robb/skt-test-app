package nearsoft.skt.test;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by rfonseca on 6/15/17.
 */
@Component
@Profile("consumer")
public class MessageReader implements ActionPerformer {

    @Autowired
    private QueueConnectionFactory connectionFactory;

    private static final Logger LOGGER = Logger.getLogger(QueueConnectionFactory.class);

    public void read() throws IOException, TimeoutException {
        Channel channel = connectionFactory.getChannel();
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                LOGGER.debug(" Received '" + message + "'");
            }
        };
        channel.basicConsume(connectionFactory.getQueueName(), true, consumer);
    }

    @Override
    public void execute(int times) {
        try {
            read();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } catch (TimeoutException e) {
            LOGGER.error(e.getMessage());
        }
    }
}