package com.leon.cloud.audit.dao;


import com.leon.cloud.audit.entity.CfgAuditEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CfgAuditMapper {
    CfgAuditEntity getCfgAuditByAuditId(@Param("auditId")Long auditId);

    Integer updateModifyDate(@Param("params")CfgAuditEntity cfgAuditEntity);
}
