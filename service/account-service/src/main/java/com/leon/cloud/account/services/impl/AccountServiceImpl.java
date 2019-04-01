package com.leon.cloud.account.services.impl;

import com.leon.cloud.account.domain.Account;
import com.leon.cloud.account.domain.User;
import com.leon.cloud.account.repository.AccountRepository;
import com.leon.cloud.account.services.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * Created by leon on 2019/3/5.
 */

@Log4j2
@Service
public class AccountServiceImpl implements AccountService{
    private final AccountRepository repository;

    @Autowired
    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Account findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Account create(User user) {
        Account existing = repository.findByName(user.getUsername());
        Assert.isNull(existing, "account already exists: " + user.getUsername());
        Account account=new Account();
        account.setName(user.getUsername());
        account.setPassword(user.getPassword());
        account.setToken(user.getUsername()+"_token");
        account.setCreateDate(new Date());
        account.setUpdateDate(new Date());
        account.setState(1);
        log.info("new account has been created: " + account.getName());
        repository.saveAndFlush(account);
        return account;
    }
}
