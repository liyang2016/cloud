package com.leon.cloud.serial.service.factory;

import com.leon.cloud.serial.constant.ProviderType;
import com.leon.cloud.serial.service.IdService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;

/**
 * Created by leon on 2019/3/14.
 */
public class IdServiceBeanFactory implements FactoryBean<IdService> {
    private ProviderType providerType;
    /**
     * 初始化方法
     */
    public void init(){

    }

    @Nullable
    @Override
    public IdService getObject() throws Exception {
        return null;
    }

    @Nullable
    @Override
    public Class<?> getObjectType() {
        return IdService.class;
    }

    /**
     * 创建单例bean
     * @return
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}
