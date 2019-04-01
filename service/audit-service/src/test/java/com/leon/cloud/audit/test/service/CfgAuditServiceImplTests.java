package com.leon.cloud.audit.test.service;


import com.leon.cloud.audit.AuditApplication;
import com.leon.cloud.audit.entity.CfgAuditEntity;
import com.leon.cloud.audit.service.ICfgAuditService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuditApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CfgAuditServiceImplTests {

    @Autowired
    private ICfgAuditService cfgAuditService;

    @Test(expected=Exception.class)
    @Transactional
    public void testUpdateModifyDate() throws Exception {
        CfgAuditEntity cfgAuditEntity=new CfgAuditEntity();
        cfgAuditEntity.setAuditId(1001L);
        cfgAuditService.updateModifyDate(cfgAuditEntity);
        throw new Exception();
    }
}
