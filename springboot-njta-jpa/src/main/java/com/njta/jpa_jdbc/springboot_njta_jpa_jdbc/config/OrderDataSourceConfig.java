package com.njta.jpa_jdbc.springboot_njta_jpa_jdbc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.Map;

/**
 * 数据库2的源配置
 * @author chc
 * @create 2019-02-05 20:10
 **/
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "orderEntityManagerFactory",   //EntityManagerFactory引用
        transactionManagerRef = "orderTransactionManager",      //transactionManager引用
        basePackages = {"com.njta.jpa_jdbc.springboot_njta_jpa_jdbc.dao.order"}) // 设置Repository所在位置
public class OrderDataSourceConfig {
    @Autowired
    private JpaProperties jpaProperties;


    @Autowired
    @Qualifier("orderDataSource")
    private DataSourceProperties orderDataSourceProperties;
    /**
     * 我们通过LocalContainerEntityManagerFactoryBean来获取EntityManagerFactory实例
     * @return
     */
    @Bean(name = "orderEntityManagerFactoryBean")
    //@Primary
    public LocalContainerEntityManagerFactoryBean orderEntityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(orderDataSourceProperties.initializeDataSourceBuilder().build())
                .properties(getVendorProperties())
                .packages("com.njta.jpa_jdbc.springboot_njta_jpa_jdbc.model.order") //设置实体类所在位置
                .persistenceUnit("userPersistenceUnit")
                .build();
        //.getObject();//不要在这里直接获取EntityManagerFactory
    }

    /**
     *  这里其实不需要配置数据库的方言.
     *  像hibernate.hbm2ddl.auto 可以在这里配置.但是我的是在application.properties中配置的.
     */
    private Map<String, Object> getVendorProperties() {
        HibernateSettings hibernateSettings = new HibernateSettings();
        return jpaProperties.getHibernateProperties(hibernateSettings);
    }
    /**
     * EntityManagerFactory类似于Hibernate的SessionFactory,mybatis的SqlSessionFactory
     * 总之,在执行操作之前,我们总要获取一个EntityManager,这就类似于Hibernate的Session,
     * mybatis的sqlSession.
     * @param builder
     * @return
     */
    @Bean(name = "orderEntityManagerFactory")
    public EntityManagerFactory orderEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return this.orderEntityManagerFactoryBean(builder).getObject();
    }

    /**
     * 配置事物管理器
     * @return
     */
    @Bean(name = "orderTransactionManager")
    public PlatformTransactionManager writeTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(orderEntityManagerFactory(builder));
    }
}
