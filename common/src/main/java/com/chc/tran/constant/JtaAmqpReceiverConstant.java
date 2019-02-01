package com.chc.tran.constant;

/**
 * jta的接收结果的rabbitmq的信息
 * @author chc
 * @create 2019-02-01 11:24
 **/
public interface JtaAmqpReceiverConstant {
    String EXCHANGE_NAME = "jta_amqp_receiver_exchane";
    String QUEUE_NAME="jta_amqp_receiver_queue";
    String ROUTING_KEY = "jta_amqp_receiver_routingKey";
}
