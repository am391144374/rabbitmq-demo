package com.lmq.core;

import com.lmq.bean.MqPropertiesBean;
import com.lmq.bean.PropertiesBean;
import com.lmq.contains.MqContain;
import com.lmq.core.listerenMsg.DefaultListerenMsg;
import com.lmq.exception.PropertiestIsNullException;
import com.lmq.resouce.MqDefaultResource;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;


@Configuration
public class RabbitmqConfig {

    private MqPropertiesBean mqPropertiesBean;

    RabbitmqConfig(){
        MqDefaultResource mqDefaultResource = new MqDefaultResource();
        mqDefaultResource.loadPerties();
        try {
            this.mqPropertiesBean = (MqPropertiesBean) mqDefaultResource.getProperties();
        } catch (PropertiestIsNullException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(mqPropertiesBean.getHost());
        connectionFactory.setPort(Integer.parseInt(mqPropertiesBean.getPort()));
        connectionFactory.setUsername(mqPropertiesBean.getUserName());
        connectionFactory.setPassword(mqPropertiesBean.getPasswd());
        connectionFactory.setVirtualHost(mqPropertiesBean.getVhost());
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setReceiveTimeout(MqContain.RECIVETIMEOUT);
        return rabbitTemplate;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        rabbitAdmin.declareQueue(queue());
        rabbitAdmin.declareExchange(exchange());
        rabbitAdmin.declareBinding(binding());
        return rabbitAdmin;
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
        simpleMessageListenerContainer.addQueues(queue());
        simpleMessageListenerContainer.setMessageListener(new DefaultListerenMsg());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setAutoStartup(true);
        return simpleMessageListenerContainer;
    }

    @Bean
    public Queue queue(){
        return new Queue(mqPropertiesBean.getQueueName(),false);
    }

    @Bean
    public Exchange exchange(){
        return new DirectExchange(mqPropertiesBean.getExchangeName(),false,false);
    }

    @Bean
    public Binding binding(){
        return new Binding(mqPropertiesBean.getQueueName(), Binding.DestinationType.QUEUE,mqPropertiesBean.getExchangeName(),mqPropertiesBean.getRoutingKey(),new HashMap<>());
    }

}
