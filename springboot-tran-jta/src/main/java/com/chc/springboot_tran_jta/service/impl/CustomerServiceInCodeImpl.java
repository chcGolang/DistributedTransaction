package com.chc.springboot_tran_jta.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.chc.springboot_tran_jta.dao.CustomerRepository;
import com.chc.springboot_tran_jta.model.Customer;
import com.chc.springboot_tran_jta.service.CustomerServiceInCode;
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
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 *
 * 代码的方式使用事务
 * @author chc
 * @create 2019-01-27 15:23
 **/
@Service
public class CustomerServiceInCodeImpl implements CustomerServiceInCode {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    PlatformTransactionManager platformTransactionManager;

    @Autowired
    RabbitTemplate rabbitTemplate;

    public Customer create(Customer customer) throws Exception{
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        // 设置事务隔离级别
        def.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
        // 设置传播机制
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus transaction = platformTransactionManager.getTransaction(def);

        try {
            customerRepository.save(customer);
            // 异常模拟
            //simulateError();
            // 提交事务
            platformTransactionManager.commit(transaction);
            return customer;
        } catch (Exception e) {
            e.printStackTrace();
            // 回滚
            platformTransactionManager.rollback(transaction);
        }
        return null;
    }

    private void simulateError()throws Exception{
        throw new Exception("代码事务错误模拟");
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = JtaAmqpCodeConstant.EXCHANGE_NAME,type = ExchangeTypes.DIRECT),
            value = @Queue(value = JtaAmqpCodeConstant.QUEUE_NAME,durable = "true"),
            key = JtaAmqpCodeConstant.ROUTING_KEY
    ))
    @RabbitHandler
    public void receiverCode(@Payload Customer customer, Message message, Channel channel)throws Exception{
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        // 设置事务隔离级别
        //def.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
        // 设置传播机制
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus transaction = platformTransactionManager.getTransaction(def);
        try {
            String format = String.format("Code 操作回送信息:%s", JSONObject.toJSONString(customer));
            rabbitTemplate.setChannelTransacted(true);
            rabbitTemplate.convertAndSend(JtaAmqpReceiverConstant.EXCHANGE_NAME,JtaAmqpReceiverConstant.ROUTING_KEY,format);
            customerRepository.save(customer);
            platformTransactionManager.commit(transaction);
        }catch (Exception e){
            //e.printStackTrace();
            platformTransactionManager.rollback(transaction);
        }finally {
            long deliveryTag = message.getMessageProperties().getDeliveryTag();
            channel.basicAck(deliveryTag,false);
        }

    }

}
