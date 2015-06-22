package io.pivotal.ce.fileproc;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by cq on 21/6/15.
 */
@SpringBootApplication
public class App {

    @Autowired
    ConnectionFactory connectionFactory;

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(RabbitConfig.QUEUE_NAME);
        container.setMessageListener(listener());
        return container;
    }
    @Bean
    public MessageListener listener() {
        return new MessageListener() {
            public void onMessage(Message message) {
                System.out.println("received: " + message);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }


}
