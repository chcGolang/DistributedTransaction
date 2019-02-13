package com.chc.springboot_axon.account.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.commandhandling.TargetAggregateIdentifier;


/**
 * 创建account
 * @author chc
 * @create 2019-02-13 11:05
 **/
@Data
@AllArgsConstructor
public class AccountCreateCommand {
    /**
     * id
     */
    @TargetAggregateIdentifier
    private String accountId;

}
