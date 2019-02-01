package com.chc.springboot_tran_jta.service;


import com.chc.springboot_tran_jta.model.Customer;

/**
 * 代码的方式使用事务
 * @author chc
 * @create 2019-01-27 16:26
 **/
public interface CustomerServiceInCode {
    Customer create(Customer customer)throws Exception;
}
