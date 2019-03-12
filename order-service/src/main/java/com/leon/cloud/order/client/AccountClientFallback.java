package com.leon.cloud.order.client;

import com.leon.cloud.order.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by leon on 2019/3/6.
 */

@Component
public class AccountClientFallback implements AccountClient{
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountClientFallback.class);

    @Override
    public Account getAccountByName(String userName) {
        LOGGER.error("remote invoke error,use default user");
        Account account=new Account();
        account.setUserId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
        account.setState(0);
        return account;
    }
}
