package com.chc.springboot_axon.account.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * 存款
 * @author chc
 * @create 2019-02-13 11:07
 **/
@Data
@AllArgsConstructor
public class AccountDepositCommand {
    /**
     * id
     */
    @TargetAggregateIdentifier
    private String accountId;

    /**
     * 存款的金额
     */
    private Double amount;
}
