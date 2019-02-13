package com.chc.springboot_axon.account.query;

import lombok.Data;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author chc
 * @create 2019-02-13 12:39
 **/
@Entity(name = "axon_account")
@Data
public class AccountEntity {
    /**
     * id
     */
    @Id
    private String accountId;

    /**
     * 余额
     */
    private Double deposit;
}
