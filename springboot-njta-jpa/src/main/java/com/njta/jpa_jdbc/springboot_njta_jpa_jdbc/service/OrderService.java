package com.njta.jpa_jdbc.springboot_njta_jpa_jdbc.service;

import com.njta.jpa_jdbc.springboot_njta_jpa_jdbc.dao.order.OrderRepository;
import com.njta.jpa_jdbc.springboot_njta_jpa_jdbc.dao.user.UserRepository;
import com.njta.jpa_jdbc.springboot_njta_jpa_jdbc.model.order.Order;
import com.njta.jpa_jdbc.springboot_njta_jpa_jdbc.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chc
 * @create 2019-02-05 20:49
 **/
@Service
public class OrderService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;

    // 多数据源链式事务提交
    @Transactional(rollbackFor = Exception.class,transactionManager = "chainedTransactionManager")
    public void createOrder(Order order)throws Exception{
       /* User user = userRepository.getOne(order.getUserId());
        user.setDeposit(user.getDeposit()-order.getAmount());
        userRepository.save(user);*/
        userRepository.updateToDeposit(order.getAmount(),order.getUserId());
        if (order.getTitle().equals("error1")){
            throw new Exception("error1");
        }
        orderRepository.save(order);
        if (order.getTitle().equals("error2")){
            throw new Exception("error2");
        }
    }
}
