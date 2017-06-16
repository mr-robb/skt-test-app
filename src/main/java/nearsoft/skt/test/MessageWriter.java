package nearsoft.skt.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by rfonseca on 6/15/17.
 */
@Profile("publisher")
@Component
public class MessageWriter implements ActionPerformer{

    private static final Logger LOGGER = Logger.getLogger(MessageWriter.class);
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private QueueConnectionFactory connectionFactory;


    public void write(int timesToSend) throws IOException, TimeoutException {
        Channel channel = connectionFactory.getChannel();
        MyMessage message = new MyMessage();
        message.setCurrentTime(System.currentTimeMillis());
        message.setHeader("Content-type:application/json");
        PayloadMessage payloadMessage = new PayloadMessage();
        payloadMessage.setMessageValue("Hola Vatos");
        message.setMessage(payloadMessage);

        for( int count=0;count<timesToSend;count++ ) {
            channel.basicPublish("", connectionFactory.getQueueName(), null, mapper.writeValueAsBytes(message));
            LOGGER.debug(" Sent '" + message + "' to Queue "+ connectionFactory.getQueueName());
        }
        connectionFactory.close();
    }

    @Override
    public void execute(int times) {
        try {
            write(times);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } catch (TimeoutException e) {
            LOGGER.error(e.getMessage());
        }finally {
            LOGGER.info("The application is getting down");
            System.exit(0);
        }
    }
}