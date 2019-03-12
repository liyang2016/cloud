package com.leon.cloud.account.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created by leon on 2019/3/5.
 */

@Setter
@Getter
@ToString
public class User {

    @NotNull
    @Length(min = 3, max = 20)
    private String username;

    @NotNull
    @Length(min = 6, max = 40)
    private String password;
}
