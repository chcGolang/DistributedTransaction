package com.chc.springboot_tx_jpa.dao;

import com.chc.springboot_tx_jpa.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chc
 * @create 2019-01-27 15:21
 **/
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
