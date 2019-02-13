package com.chc.springboot_axon.account.query;

import com.chc.springboot_axon.account.Account;
import com.chc.springboot_axon.account.command.AccountCreateCommand;
import com.chc.springboot_axon.account.command.AccountDepositCommand;
import com.chc.springboot_axon.account.event.AccountCreateedEvent;
import com.chc.springboot_axon.account.event.AccountDepositedEvent;
import com.chc.springboot_axon.account.event.AccountWithdrawedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chc
 * @create 2019-02-13 12:42
 **/
@Component
public class AccountEntityProjector {
    @Autowired
    AccountEntityRepository accountEntityRepository;

    @EventHandler
    public void on(AccountCreateedEvent command){
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountId(command.getAccountId());
        accountEntityRepository.save(accountEntity);

    }

    @EventHandler
    public void on(AccountDepositedEvent command){
        AccountEntity accountEntity = accountEntityRepository.findOneByAccountId(command.getAccountId());
        accountEntity.setDeposit(accountEntity.getDeposit()+command.getAmount());
        accountEntityRepository.save(accountEntity);
    }

    @EventHandler
    public void on(AccountWithdrawedEvent command){
        AccountEntity accountEntity = accountEntityRepository.findOneByAccountId(command.getAccountId());
        accountEntity.setDeposit(accountEntity.getDeposit()-command.getAmount());
        accountEntityRepository.save(accountEntity);
    }
}
