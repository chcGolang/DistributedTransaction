package com.chc.springboot_tx_jpa.service;

import com.chc.springboot_tx_jpa.model.Customer;

/**
 * 注解的方式使用事务
 * @author chc
 * @create 2019-01-27 16:26
 **/
public interface CustomerServiceInAnnotation {
    Customer create(Customer customer)throws Exception;
}
