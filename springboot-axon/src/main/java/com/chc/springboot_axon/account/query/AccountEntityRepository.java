package com.chc.springboot_axon.account.query;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chc
 * @create 2019-02-13 12:41
 **/
public interface AccountEntityRepository extends JpaRepository<AccountEntity,String> {

    AccountEntity findOneByAccountId(String accountId);
}
