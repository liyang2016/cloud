/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : audit

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2019-03-28 10:02:18
*/

DROP database IF EXISTS `audit`;
create database `audit` default character set utf8 collate utf8_general_ci;
-- change database
use audit;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cfg_audit
-- ----------------------------
DROP TABLE IF EXISTS `cfg_audit`;
CREATE TABLE `cfg_audit` (
   `audit_id` bigint(20) unsigned NOT NULL COMMENT '唯一标识',
   `audit_name` varchar(255) DEFAULT NULL,
   `process_id` bigint(14) DEFAULT NULL,
   `busi_type` bigint(14) DEFAULT NULL,
   `audit_clazz` varchar(255) DEFAULT NULL,
   `audit_url` varchar(255) DEFAULT NULL,
   `audit_desc` varchar(1000) DEFAULT NULL,
   `last_extract_date` datetime DEFAULT NULL,
   `quartz_task_type` tinyint(2) DEFAULT NULL,
   `state` tinyint(2) DEFAULT NULL,
   `create_date` datetime DEFAULT NULL,
   `creator` bigint(20) DEFAULT NULL,
   PRIMARY KEY (`audit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='稽核配置表';

-- ----------------------------
-- Table structure for cfg_audit_item
-- ----------------------------
DROP TABLE IF EXISTS `cfg_audit_item`;
CREATE TABLE `cfg_audit_item` (
  `audit_item_id` bigint(20) NOT NULL,
  `audit_id` bigint(20) DEFAULT NULL,
  `busi_id` bigint(20) DEFAULT NULL,
  `busi_oper_id` bigint(20) DEFAULT NULL,
  `audit_item_name` varchar(255) DEFAULT NULL,
  `audit_item_desc` varchar(1000) DEFAULT NULL,
  `audit_item_url` varchar(255) DEFAULT NULL,
  `audit_item_param` varchar(1000) DEFAULT NULL,
  `audit_item_order` int(4) DEFAULT NULL,
  `audit_file_type` int(4) DEFAULT NULL,
  `audit_file_id` bigint(20) DEFAULT NULL,
  `state` tinyint(2) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`audit_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
