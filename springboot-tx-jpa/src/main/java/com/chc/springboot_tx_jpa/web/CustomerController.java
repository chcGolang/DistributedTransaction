package com.chc.springboot_tx_jpa.web;

import com.chc.springboot_tx_jpa.dao.CustomerRepository;
import com.chc.springboot_tx_jpa.model.Customer;
import com.chc.springboot_tx_jpa.service.impl.CustomerServiceInAnnotationImpl;
import com.chc.springboot_tx_jpa.service.impl.CustomerServiceInCodeImpl;
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
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerServiceInCodeImpl customerServiceInCode;
    @Autowired
    CustomerServiceInAnnotationImpl customerServiceInAnnotation;

    @Autowired
    CustomerRepository customerRepository;

    // 事务为代码的方式
    @PostMapping("/code")
    public Customer createInCode(@RequestBody Customer customer)throws Exception{
        return customerServiceInCode.create(customer);

    }

    // 事务为注解的方式
    @PostMapping("/annotation")
    public Customer createInAnnotation(@RequestBody Customer customer)throws Exception{
        return customerServiceInAnnotation.create(customer);

    }

    @GetMapping("/getAll")
    public List<Customer> getAll(){
       return customerRepository.findAll();
    }




}
