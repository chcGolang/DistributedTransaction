package com.njta.jpa_jdbc.springboot_njta_jpa_jdbc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * DataSource配置
 * @author chc
 * @create 2019-02-05 19:30
 **/
@Configuration
public class DruidDataSourceConfig {

    @Primary // 同一个类型有多个实例
    @Bean(name = "userDataSource")
    @ConfigurationProperties(prefix = "spring.ds-user")
    public DataSourceProperties userDataSource(){
        return new DataSourceProperties();
    }

    @Bean(name = "orderDataSource")
    @ConfigurationProperties(prefix = "spring.ds-order")
    public DataSourceProperties orderDataSource(){
        return new DataSourceProperties();
    }

}
