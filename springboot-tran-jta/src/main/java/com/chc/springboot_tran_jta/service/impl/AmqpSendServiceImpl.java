package com.chc.springboot_tran_jta.service.impl;

import com.chc.springboot_tran_jta.model.Customer;
import com.chc.springboot_tran_jta.service.AmqpSendService;
import com.chc.tran.constant.JtaAmqpAnnotationConstant;
import com.chc.tran.constant.JtaAmqpCodeConstant;
import com.chc.tran.constant.JtaAmqpReceiverConstant;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;

/**
 * @author chc
 * @create 2019-02-01 22:26
 **/
@Service
public class AmqpSendServiceImpl implements AmqpSendService {
    @Autowired
    RabbitTemplate rabbitTemplateAnnotation;

    @Autowired
    RabbitTemplate rabbitTemplateCode;
    @Override
    public void sendAnnotation(Customer customer) throws Exception {
        rabbitTemplateAnnotation.setQueue(JtaAmqpAnnotationConstant.QUEUE_NAME);
        rabbitTemplateAnnotation.convertAndSend(JtaAmqpAnnotationConstant.EXCHANGE_NAME,JtaAmqpAnnotationConstant.ROUTING_KEY,customer);
    }

    @Override
    public void sendCode(Customer customer) throws Exception {
        rabbitTemplateCode.setQueue(JtaAmqpCodeConstant.QUEUE_NAME);
        rabbitTemplateCode.convertAndSend(JtaAmqpCodeConstant.EXCHANGE_NAME,JtaAmqpCodeConstant.ROUTING_KEY,customer);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = JtaAmqpReceiverConstant.EXCHANGE_NAME,type = ExchangeTypes.DIRECT),
            value = @Queue(value = JtaAmqpReceiverConstant.QUEUE_NAME,durable = "true"),
            key = JtaAmqpReceiverConstant.ROUTING_KEY
    ))
    @RabbitHandler
    public void receiver(Message message, Channel channel)throws Exception{
        byte[] body = message.getBody();
        System.err.println(new String(body));
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag,false);
    }
}
