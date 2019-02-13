package com.njta.jpa_jdbc.springboot_njta_jpa_jdbc.model.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author chc
 * @create 2019-02-05 20:30
 **/
@Data
@Entity(name = "user_info")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String userName;
    /**
     * 余额
     */
    @Column
    private Integer deposit;
}
