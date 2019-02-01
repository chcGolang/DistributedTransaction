package com.chc.springboot_tran_jta.service;

import com.chc.springboot_tran_jta.model.Customer;

/**
 * @author chc
 * @create 2019-02-01 22:25
 **/
public interface AmqpSendService {

    void sendAnnotation(Customer customer) throws Exception;

    void sendCode(Customer customer) throws Exception;
}
