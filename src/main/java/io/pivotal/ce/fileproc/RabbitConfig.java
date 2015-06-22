package io.pivotal.ce.fileproc;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.common.AmqpServiceInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cq on 21/6/15.
 */
@Configuration
public class RabbitConfig {

        @Bean
        public ConnectionFactory rabbitConnectionFactory() {
            CloudFactory cloudFactory = new CloudFactory();
            Cloud cloud = cloudFactory.getCloud();
            AmqpServiceInfo serviceInfo = (AmqpServiceInfo) cloud.getServiceInfo("rabbit-files");
            String serviceID = serviceInfo.getId();
            return cloud.getServiceConnector(serviceID, ConnectionFactory.class, null);
        }


        @Bean
        public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
            return new RabbitTemplate(connectionFactory);
        }

}
