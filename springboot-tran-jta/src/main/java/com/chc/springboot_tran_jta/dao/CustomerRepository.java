package com.chc.springboot_tran_jta.dao;


import com.chc.springboot_tran_jta.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chc
 * @create 2019-01-27 15:21
 **/
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
