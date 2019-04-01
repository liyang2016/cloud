package com.leon.cloud.audit.service;

import com.leon.cloud.audit.entity.CfgAuditEntity;

public interface ICfgAuditService {

    CfgAuditEntity getCfgAuditByAuditId(Long auditId);

    CfgAuditEntity updateModifyDate(CfgAuditEntity cfgAuditEntity);
}
