package com.leon.cloud.audit.config;

import lombok.extern.log4j.Log4j2;

import java.util.concurrent.atomic.AtomicInteger;

@Log4j2
public class CloudRouterDataSourceHolder {

    private static final ThreadLocal<DBTypeEnum> dataSourceHolder = new ThreadLocal<>();
    private static AtomicInteger count = new AtomicInteger(0);

    public static DBTypeEnum get() {
        return dataSourceHolder.get();
    }

    public static void set(DBTypeEnum dbTypeEnum) {
        dataSourceHolder.set(dbTypeEnum);
    }

    public static void master() {
        log.info("use master database");
        set(DBTypeEnum.MASTER);
    }

    public static void slave() {
        int index = count.getAndIncrement() % 2;
        if (count.get() > 9999) {
            count.set(0);
        }
        if (index == 0) {
            log.info("use slave_1 database");
            set(DBTypeEnum.SLAVE1);
        } else {
            log.info("use slave_2 database");
            set(DBTypeEnum.SLAVE2);
        }
    }
}
