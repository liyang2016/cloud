package com.leon.cloud.audit.service.impl;

import com.leon.cloud.audit.dao.CfgAuditMapper;
import com.leon.cloud.audit.entity.CfgAuditEntity;
import com.leon.cloud.audit.service.ICfgAuditService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Log4j2
public class CfgAuditServiceImpl implements ICfgAuditService {

    @Autowired
    private CfgAuditMapper cfgAuditMapper;



    public CfgAuditEntity getCfgAuditByAuditId(Long auditId){
        CfgAuditEntity cfgAuditEntity=new CfgAuditEntity();
        try{
            cfgAuditEntity=cfgAuditMapper.getCfgAuditByAuditId(auditId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return cfgAuditEntity;
    }

    @Override
    public CfgAuditEntity updateModifyDate(CfgAuditEntity cfgAuditEntity) {
        cfgAuditEntity.setCreateDate(new Date());
        try{
            cfgAuditEntity=cfgAuditMapper.updateModifyDate(cfgAuditEntity);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return cfgAuditEntity;
    }

}
