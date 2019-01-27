package com.chc.springboot_tx_jpa.service.impl;

import com.chc.springboot_tx_jpa.dao.CustomerRepository;
import com.chc.springboot_tx_jpa.model.Customer;
import com.chc.springboot_tx_jpa.service.CustomerServiceInCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
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

}
