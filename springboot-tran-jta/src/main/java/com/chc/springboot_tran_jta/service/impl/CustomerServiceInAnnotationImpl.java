package com.chc.springboot_tran_jta.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.chc.springboot_tran_jta.dao.CustomerRepository;
import com.chc.springboot_tran_jta.model.Customer;
import com.chc.springboot_tran_jta.service.CustomerServiceInAnnotation;
import com.chc.tran.constant.JtaAmqpAnnotationConstant;
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
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 注解的方式使用事务
 * @author chc
 * @create 2019-01-27 15:23
 **/
@Service
public class CustomerServiceInAnnotationImpl implements CustomerServiceInAnnotation {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Transactional(rollbackFor=Exception.class)
    public Customer create(Customer customer)throws Exception{
        Customer save = customerRepository.save(customer);
        //simulateError();
        return save;
    }

    private void simulateError()throws Exception{
        throw new Exception("注解事务错误模拟");
    }

    @Transactional
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = JtaAmqpAnnotationConstant.EXCHANGE_NAME,type = ExchangeTypes.DIRECT),
            value = @Queue(value = JtaAmqpAnnotationConstant.QUEUE_NAME,durable = "true"),
            key = JtaAmqpAnnotationConstant.ROUTING_KEY
    ))
    @RabbitHandler
    public void receiverAnnotation(@Payload Customer customer, Message message, Channel channel)throws Exception{
        try {
            String format = String.format("Annotation 操作回送信息:%s", JSONObject.toJSONString(customer));
            rabbitTemplate.setChannelTransacted(true);
            rabbitTemplate.convertAndSend(JtaAmqpReceiverConstant.EXCHANGE_NAME,JtaAmqpReceiverConstant.ROUTING_KEY,format);
            customerRepository.save(customer);
        } finally {
            long deliveryTag = message.getMessageProperties().getDeliveryTag();
            channel.basicAck(deliveryTag,false);
        }

    }

}
