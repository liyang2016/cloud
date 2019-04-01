package com.leon.cloud.audit.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class CfgAuditEntity {
//`audit_id` bigint(20) unsigned NOT NULL COMMENT '唯一标识',
//            `audit_name` varchar(255) DEFAULT NULL,
//  `process_id` bigint(14) DEFAULT NULL,
//  `busi_type` bigint(14) DEFAULT NULL,
//  `audit_clazz` varchar(255) DEFAULT NULL,
//  `audit_url` varchar(255) DEFAULT NULL,
//  `audit_desc` varchar(1000) DEFAULT NULL,
//  `last_extract_date` datetime DEFAULT NULL,
//            `quartz_task_type` tinyint(2) DEFAULT NULL,
//  `state` tinyint(2) DEFAULT NULL,
//  `create_date` datetime DEFAULT NULL,
//            `creator` bigint(20) DEFAULT NULL,
    private Long auditId;
    private String auditName;
    private Long processId;
    private Date createDate;


}
