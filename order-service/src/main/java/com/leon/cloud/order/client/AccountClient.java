package com.leon.cloud.order.client;

import com.leon.cloud.order.domain.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by leon on 2019/3/6.
 */

@FeignClient(name = "account-service", fallback = AccountClientFallback.class)
public interface AccountClient {

    @RequestMapping(method = RequestMethod.GET, value = "/accounts/user/{name}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Account getAccountByName(@PathVariable("name") String userName);
}
