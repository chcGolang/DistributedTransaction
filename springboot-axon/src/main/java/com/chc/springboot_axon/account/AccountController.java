package com.chc.springboot_axon.account;

import com.chc.springboot_axon.account.command.AccountCreateCommand;
import com.chc.springboot_axon.account.command.AccountDepositCommand;
import com.chc.springboot_axon.account.command.AccountWithdrawCommand;
import com.chc.springboot_axon.account.query.AccountEntity;
import com.chc.springboot_axon.account.query.AccountEntityRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author chc
 * @create 2019-02-13 11:53
 **/
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    CommandGateway commandGateway;

    @Autowired
    AccountEntityRepository accountEntityRepository;

    @PostMapping("/create")
    public CompletableFuture<Object> create()throws Exception{
        String accountId = UUID.randomUUID().toString();
        AccountCreateCommand command = new AccountCreateCommand(accountId);
        return commandGateway.send(command);

    }

    @PutMapping("/{accountId}/deposit/{amount}")
    public CompletableFuture<Object> deposit(@PathVariable String accountId,@PathVariable Double amount)throws Exception{
         return commandGateway.send(new AccountDepositCommand(accountId, amount));
    }

    @PutMapping("/{accountId}/withdraw/{amount}")
    public CompletableFuture<Object> withdraw(@PathVariable String accountId,@PathVariable Double amount)throws Exception{
        return commandGateway.send(new AccountWithdrawCommand(accountId, amount));
    }

    @GetMapping("/all")
    public List<AccountEntity> all(){
       return accountEntityRepository.findAll();
    }
}
