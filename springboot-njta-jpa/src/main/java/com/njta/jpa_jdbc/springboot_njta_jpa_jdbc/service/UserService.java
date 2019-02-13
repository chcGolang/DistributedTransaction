package com.njta.jpa_jdbc.springboot_njta_jpa_jdbc.service;

import com.njta.jpa_jdbc.springboot_njta_jpa_jdbc.dao.user.UserRepository;
import com.njta.jpa_jdbc.springboot_njta_jpa_jdbc.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chc
 * @create 2019-02-05 20:47
 **/
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public void createUser(User user)throws Exception{
        userRepository.save(user);
        if(user.getUserName().equals("error1")){
            throw new Exception("error1");
        }
    }
}
