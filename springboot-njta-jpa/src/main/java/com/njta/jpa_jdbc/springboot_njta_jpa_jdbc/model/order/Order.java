package com.njta.jpa_jdbc.springboot_njta_jpa_jdbc.model.order;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.swing.border.TitledBorder;
import java.util.List;

/**
 * @author chc
 * @create 2019-02-05 20:32
 **/
@Data
@Entity(name = "order_info")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Long userId;
    @Column
    private String title;
    @Column
    private Integer amount;
}
