package com.njta.jpa_jdbc.springboot_njta_jpa_jdbc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;

/**
 * 创建链式事务
 * @author chc
 * @create 2019-02-05 22:22
 **/
@Configuration
public class ChainedTransactionConfig {
    @Autowired
    @Qualifier("userTransactionManager")
    PlatformTransactionManager userTransactionManager;

    @Autowired
    @Qualifier("orderTransactionManager")
    PlatformTransactionManager orderTransactionManager;

    @Bean(name = "chainedTransactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(){
        /*JpaTransactionManager order = new JpaTransactionManager(orderEntityManagerFactory);
        JpaTransactionManager user = new JpaTransactionManager(userEntityManagerFactory);*/
        ChainedTransactionManager chainedTransactionManager = new ChainedTransactionManager(userTransactionManager,orderTransactionManager);
        return chainedTransactionManager;
    }
}
