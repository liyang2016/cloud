package com.leon.cloud.audit.service.impl;

import com.leon.cloud.audit.dao.CfgAuditMapper;
import com.leon.cloud.audit.entity.CfgAuditEntity;
import com.leon.cloud.audit.service.ICfgAuditService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Log4j2
public class CfgAuditServiceImpl implements ICfgAuditService {

    @Autowired
    private CfgAuditMapper cfgAuditMapper;



    @Override
    public CfgAuditEntity getCfgAuditByAuditId(Long auditId){
        CfgAuditEntity cfgAuditEntity=new CfgAuditEntity();
        try{
            cfgAuditEntity=cfgAuditMapper.getCfgAuditByAuditId(auditId);
//            throw new Exception();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return cfgAuditEntity;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public CfgAuditEntity updateModifyDate(CfgAuditEntity cfgAuditEntity) {
        //根据主键获取
        CfgAuditEntity entity=cfgAuditMapper.getCfgAuditByAuditId(cfgAuditEntity.getAuditId());
        entity.setCreateDate(new Date());
        log.info(cfgAuditMapper.updateModifyDate(entity));
        entity=cfgAuditMapper.getCfgAuditByAuditId(cfgAuditEntity.getAuditId());
        return entity;
    }

}
