package com.chc.springboot_tran_jta.web;


import com.chc.springboot_tran_jta.dao.CustomerRepository;
import com.chc.springboot_tran_jta.model.Customer;
import com.chc.springboot_tran_jta.service.AmqpSendService;
import com.chc.springboot_tran_jta.service.impl.CustomerServiceInAnnotationImpl;
import com.chc.springboot_tran_jta.service.impl.CustomerServiceInCodeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chc
 * @create 2019-01-27 15:45
 **/
@RestController
@RequestMapping("/jta")
public class CustomerJtaController {


    @Autowired
    AmqpSendService amqpSendService;

    @Autowired
    CustomerRepository customerRepository;

    // 事务为代码的方式
    @PostMapping("/code")
    public void createInCode(@RequestBody Customer customer)throws Exception{
        amqpSendService.sendCode(customer);

    }

    // 事务为注解的方式
    @PostMapping("/annotation")
    public void createInAnnotation(@RequestBody Customer customer)throws Exception{
         amqpSendService.sendAnnotation(customer);

    }


}
