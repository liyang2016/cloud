package com.leon.cloud.account.repository;

import com.leon.cloud.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by leon on 2019/3/5.
 */

@Repository
public interface AccountRepository extends JpaRepository<Account, String>, JpaSpecificationExecutor<Account>,Serializable {
    Account findByName(String name);
}
