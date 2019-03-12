package com.leon.cloud.order.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by leon on 2019/3/6.
 */
@Setter
@Getter
@ToString
public class Account {

    private String userId;
    private String name;
    private String password;

    private String token;

    private Date createDate;

    private Date updateDate;

    private Integer state;
}
