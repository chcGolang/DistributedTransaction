package com.chc.springboot_tx_jpa.service.impl;

import com.chc.springboot_tx_jpa.dao.CustomerRepository;
import com.chc.springboot_tx_jpa.model.Customer;
import com.chc.springboot_tx_jpa.service.CustomerServiceInAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Transactional(rollbackFor=Exception.class)
    public Customer create(Customer customer)throws Exception{
        Customer save = customerRepository.save(customer);
        simulateError();
        return save;
    }

    private void simulateError()throws Exception{
        throw new Exception("注解事务错误模拟");
    }

}
