package com.leon.cloud.account.services;

import com.leon.cloud.account.domain.Account;
import com.leon.cloud.account.domain.User;

/**
 * Created by leon on 2019/3/5.
 */

public interface AccountService {
    Account findByName(String name);

    Account create(User user);
}
