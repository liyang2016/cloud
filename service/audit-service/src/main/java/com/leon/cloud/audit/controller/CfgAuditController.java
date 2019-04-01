package com.leon.cloud.audit.controller;

import com.leon.cloud.audit.entity.CfgAuditEntity;
import com.leon.cloud.audit.service.ICfgAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class CfgAuditController {

    @Autowired
    private ICfgAuditService cfgAuditService;


    @GetMapping("/audit/{auditId}")
    public String getCfgAudit(@PathVariable Long auditId){
        CfgAuditEntity cfgAuditEntity=cfgAuditService.getCfgAuditByAuditId(auditId);
        return cfgAuditEntity.toString();
    }

    @PutMapping("/audit")
    public String updateCfgAudit(@Valid @RequestBody CfgAuditEntity cfgAuditEntity){
        return cfgAuditService.updateModifyDate(cfgAuditEntity).toString();
    }
}
