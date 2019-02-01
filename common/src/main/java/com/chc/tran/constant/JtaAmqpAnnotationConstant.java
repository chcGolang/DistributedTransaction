package com.chc.tran.constant;

/**
 * jta的annotation的rabbitmq的信息
 * @author chc
 * @create 2019-02-01 11:24
 **/
public interface JtaAmqpAnnotationConstant {
    String EXCHANGE_NAME = "jta_amqp_annotation_exchane";
    String QUEUE_NAME="jta_amqp_annotation_queue";
    String ROUTING_KEY = "jta_amqp_annotation_routingKey";
}
