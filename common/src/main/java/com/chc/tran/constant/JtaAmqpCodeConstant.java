package com.chc.tran.constant;

/**
 * jta的code的rabbitmq的信息
 * @author chc
 * @create 2019-02-01 11:24
 **/
public interface JtaAmqpCodeConstant {
    String EXCHANGE_NAME = "jta_amqp_code_exchane";
    String QUEUE_NAME="jta_amqp_code_queue";
    String ROUTING_KEY = "jta_amqp_code_routingKey";
}
