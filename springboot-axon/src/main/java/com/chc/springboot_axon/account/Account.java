package com.chc.springboot_axon.account;

import com.chc.springboot_axon.account.command.AccountCreateCommand;
import com.chc.springboot_axon.account.command.AccountDepositCommand;
import com.chc.springboot_axon.account.command.AccountWithdrawCommand;
import com.chc.springboot_axon.account.event.AccountCreateedEvent;
import com.chc.springboot_axon.account.event.AccountDepositedEvent;
import com.chc.springboot_axon.account.event.AccountWithdrawedEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;

import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author chc
 * @create 2019-02-13 10:38
 **/
@Data
@AllArgsConstructor
@Aggregate
public class Account {
    /**
     * id
     */
    @AggregateIdentifier
    private String accountId;

    /**
     * 余额
     */
    private Double deposit;

    protected Account()  {
    }

    /**
     * 创建
     * @param command
     */
    @CommandHandler
    public Account(AccountCreateCommand command){
        apply(new AccountCreateedEvent(command.getAccountId()));
    }
    @EventSourcingHandler
    public void on(AccountCreateedEvent createedEvent){
        this.accountId = createedEvent.getAccountId();
        this.deposit = 0d;
    }

    /**
     * 存款
     * @param command
     */
    @CommandHandler
    public void handle(AccountDepositCommand command){
        apply(new AccountDepositedEvent(command.getAccountId(),command.getAmount()));
    }
    @EventSourcingHandler
    public void on(AccountDepositedEvent event){
        this.deposit += event.getAmount();
    }

    /**
     * 取款
     * @param command
     */
    @CommandHandler
    public void handle(AccountWithdrawCommand command){
        if(this.deposit>= command.getAmount()){
            apply(new AccountWithdrawedEvent(command.getAccountId(),command.getAmount()));
        }else {
            throw new IllegalArgumentException("余额不足");
        }
    }
    @EventSourcingHandler
    public void on(AccountWithdrawedEvent event){
        this.deposit -= event.getAmount();
    }






}
