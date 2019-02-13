package com.chc.springboot_axon.account.event;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author chc
 * @create 2019-02-13 10:38
 **/
@Data
@AllArgsConstructor
public class AccountDepositedEvent {
    /**
     * id
     */
   private String accountId;
    /**
     * 金额
     */
    private Double amount;

}
