package com.chc.springboot_axon.account.event;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author chc
 * @create 2019-02-13 11:16
 **/
@Data
@AllArgsConstructor
public class AccountCreateedEvent {
    private String accountId;
}
