package com.njta.jpa_jdbc.springboot_njta_jpa_jdbc.dao.order;

import com.njta.jpa_jdbc.springboot_njta_jpa_jdbc.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chc
 * @create 2019-02-05 20:44
 **/
public interface OrderRepository extends JpaRepository<Order,Long> {
}
