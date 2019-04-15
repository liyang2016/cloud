package com.leon.cloud.audit.config;

import com.leon.cloud.common.db.CloudRouterDataSourceHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class CloudRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return CloudRouterDataSourceHolder.get();
    }
}
