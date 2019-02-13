# DistributedTransaction

## 1. 项目结构

### tran-jdbc
* java的jdbc事务的使用案例

### springboot-tx-jpa
* springboot jpa 事务的使用案例
* `com.chc.springboot_tx_jpa.service.CustomerServiceInAnnotation`:注解的方式使用事务
* `com.chc.springboot_tx_jpa.service.CustomerServiceInCode`: 代码的方式使用事务

### springboot-tx-amqp 
* springboot的rabbitmq事务案例

### springboot-tran-jta 
* 使用的是: jta-atomikos
* springboot的JTA多数据源回滚的案例(RabbitMq+mysql的JTA事务回滚)

### springboot-njta-jpa
* 多数据源链式事务提交(不使用JTA)
* springboot的链式事务多数据源回滚的案例(mysql+mysql的事务回滚)
