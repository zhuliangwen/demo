package com.demo.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        logger.info("当前数据源是：" + DynamicDataSourceContextHolder.get());
        return DynamicDataSourceContextHolder.get();
    }
}
