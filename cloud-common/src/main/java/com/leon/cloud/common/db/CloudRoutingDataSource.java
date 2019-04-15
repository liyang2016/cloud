package com.leon.cloud.common.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class CloudRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return CloudRouterDataSourceHolder.get();
    }
}
