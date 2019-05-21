package com.leon.cloud.account.controller;

import com.leon.cloud.account.domain.Account;
import com.leon.cloud.account.domain.User;
import com.leon.cloud.account.services.AccountService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.Controller;

import javax.validation.Valid;

/**
 * Created by leon on 2019/3/5.
 */

@RestController
@RequestMapping("/user")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "getAccountByName", response = Account.class,notes = "获取账户信息")
    @ApiImplicitParam(name = "name",value = "查询用户名称")
    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    public Account getAccountByName(@PathVariable String name) {
        return accountService.findByName(name);
    }

    @PostMapping(path = "/")
    public Account createNewAccount(@Valid @RequestBody User user) {
        return accountService.create(user);
    }

}
