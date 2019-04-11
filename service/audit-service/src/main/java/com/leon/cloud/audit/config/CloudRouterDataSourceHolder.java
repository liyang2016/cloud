package com.leon.cloud.audit.config;

public class CloudRouterDataSourceHolder {

    private static final ThreadLocal<DBTypeEnum> dataSourceHolder = new ThreadLocal<>();

    public static DBTypeEnum get() {
        return dataSourceHolder.get();
    }

    public static void set(DBTypeEnum dbTypeEnum) {
        dataSourceHolder.set(dbTypeEnum);
    }

    public static void master() {
        set(DBTypeEnum.MASTER);
    }

    public static void slave() {
        set(DBTypeEnum.SLAVE1);
    }
}
