package com.njta.jpa_jdbc.springboot_njta_jpa_jdbc.controller;

import com.njta.jpa_jdbc.springboot_njta_jpa_jdbc.model.order.Order;
import com.njta.jpa_jdbc.springboot_njta_jpa_jdbc.model.user.User;
import com.njta.jpa_jdbc.springboot_njta_jpa_jdbc.service.OrderService;
import com.njta.jpa_jdbc.springboot_njta_jpa_jdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chc
 * @create 2019-02-05 21:04
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @PostMapping("/createOrder")
    public void createOrder(@RequestBody Order order)throws Exception{
        orderService.createOrder(order);
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody User user)throws Exception{
        userService.createUser(user);
    }
}
