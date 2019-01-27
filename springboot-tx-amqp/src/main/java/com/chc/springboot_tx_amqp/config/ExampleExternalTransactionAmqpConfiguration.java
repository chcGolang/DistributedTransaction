package com.chc.springboot_tx_amqp.config;


import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.SimpleRoutingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * rabbitmq事务组件(rabbitTemplate.setChannelTransacted(true);开启)
 * @author chc
 * @create 2019-01-27 19:03
 **/
@Configuration
public class ExampleExternalTransactionAmqpConfiguration {

    public SimpleMessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        SimpleRoutingConnectionFactory factory = new SimpleRoutingConnectionFactory();
        container.setTransactionManager(transactionManager(factory));
        container.setChannelTransacted(true);
        return container;
    }

    @Bean
    public PlatformTransactionManager transactionManager(ConnectionFactory configuration) {
        return new RabbitTransactionManager(configuration);
    }



}
