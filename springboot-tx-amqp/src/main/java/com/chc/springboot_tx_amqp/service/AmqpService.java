package com.chc.springboot_tx_amqp.service;

import com.chc.tran.constant.AmqpConstant;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AsyncAmqpTemplate;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chc
 * @create 2019-01-27 17:45
 **/
@Service
public class AmqpService {

    @Autowired
    RabbitTemplate rabbitTemplate;



    /**
     * 发送队列消息
     * @param msg
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void send(String msg) throws Exception{
        System.out.println("发送消息:"+msg);
        // 开启事务
        rabbitTemplate.setChannelTransacted(true);
        rabbitTemplate.convertAndSend(AmqpConstant.EXCHANGE,AmqpConstant.ROUTINGKEY,msg);
        // 异常回滚
        simulateError();
    }

    /**
     *
     * @return
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(
                    value = AmqpConstant.EXCHANGE,
                    durable = "true",
                    type = ExchangeTypes.DIRECT
            ),
            value = @Queue(
                    value = AmqpConstant.QUEUE,durable = "true"
            ),
            key = AmqpConstant.ROUTINGKEY
    ))
    @RabbitHandler
    public void receiver(@Payload String msg, Message message, Channel channel)throws Exception{
        System.out.println("接收消息:"+msg);
        Long deliveryTag = message.getMessageProperties().getDeliveryTag();
        //手工ACK
        channel.basicAck(deliveryTag,false);
    }

    private void simulateError()throws Exception{
        throw new Exception("事务错误模拟");
    }
}
