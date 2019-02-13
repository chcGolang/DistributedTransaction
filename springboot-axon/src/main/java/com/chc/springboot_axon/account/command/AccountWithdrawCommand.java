package com.chc.springboot_axon.account.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * 取款
 * @author chc
 * @create 2019-02-13 11:07
 **/
@Data
@AllArgsConstructor
public class AccountWithdrawCommand {
    /**
     * id
     */
    @TargetAggregateIdentifier
    private String accountId;

    /**
     * 取款的金额
     */
    private Double amount;
}
