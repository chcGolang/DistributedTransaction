package com.chc.springboot_tx_amqp.web;

import com.chc.springboot_tx_amqp.service.AmqpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chc
 * @create 2019-01-27 18:02
 **/
@RestController
@RequestMapping("/amqp")
public class AmqpController {

    @Autowired
    AmqpService amqpService;

    @PostMapping("/send")
    public String send(String msg)throws Exception{
        amqpService.send(msg);
        return msg;
    }
}
