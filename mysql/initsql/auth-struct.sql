/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : auth

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-03-26 11:23:05
*/

-- CREATE database auth
DROP database IF EXISTS `auth`;
create database `auth` default character set utf8 collate utf8_general_ci;
-- change database
use auth;



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

-- ----------------------------
-- Table structure for cfg_audit_item_reason
-- ----------------------------
DROP TABLE IF EXISTS `cfg_audit_item_reason`;
CREATE TABLE `cfg_audit_item_reason` (
  `reason_id` bigint(20) NOT NULL,
  `audit_item_id` bigint(20) DEFAULT NULL,
  `reason_name` varchar(255) DEFAULT NULL,
  `deal_type` varchar(50) DEFAULT NULL,
  `deal_value` varchar(50) DEFAULT NULL,
  `state` tinyint(2) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`reason_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ins_audit
-- ----------------------------
DROP TABLE IF EXISTS `ins_audit`;
CREATE TABLE `ins_audit` (
  `ins_audit_id` bigint(20) NOT NULL,
  `audit_id` bigint(20) DEFAULT NULL,
  `process_id` bigint(20) DEFAULT NULL,
  `process_ins_id` bigint(20) DEFAULT NULL,
  `pk_value` bigint(20) DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `group_name` varchar(255) DEFAULT NULL,
  `revise_count` int(4) DEFAULT NULL,
  `submit_op_id` bigint(20) DEFAULT NULL,
  `state` tinyint(2) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `done_date` datetime DEFAULT NULL,
  `mark_flag` varchar(50) DEFAULT NULL,
  `owe_fee` decimal(10,2) DEFAULT NULL,
  `process_version` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`ins_audit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ins_audit_item
-- ----------------------------
DROP TABLE IF EXISTS `ins_audit_item`;
CREATE TABLE `ins_audit_item` (
  `ins_audit_item_id` bigint(20) NOT NULL,
  `ins_audit_id` bigint(20) DEFAULT NULL,
  `audit_item_id` bigint(20) DEFAULT NULL,
  `audit_item_name` varchar(255) DEFAULT NULL,
  `busi_ins_id` bigint(20) DEFAULT NULL,
  `busi_id` bigint(20) DEFAULT NULL,
  `busi_name` varchar(255) DEFAULT NULL,
  `busi_oper_id` bigint(20) DEFAULT NULL,
  `busi_oper_name` varchar(255) DEFAULT NULL,
  `audit_result` int(4) DEFAULT NULL,
  `state` tinyint(2) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `deal_oper` bigint(20) DEFAULT NULL,
  `done_date` datetime DEFAULT NULL,
  PRIMARY KEY (`ins_audit_item_id`),
  KEY `ins_audit_id_pk` (`ins_audit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ins_audit_item_reason
-- ----------------------------
DROP TABLE IF EXISTS `ins_audit_item_reason`;
CREATE TABLE `ins_audit_item_reason` (
  `ins_audit_reason_id` bigint(20) NOT NULL,
  `ins_audit_item_id` bigint(20) NOT NULL,
  `audit_item_id` bigint(20) DEFAULT NULL,
  `audit_reason_id` bigint(20) DEFAULT NULL,
  `audit_reason` varchar(255) DEFAULT NULL,
  `audit_reason_desc` varchar(255) DEFAULT NULL,
  `deal_type` varchar(255) DEFAULT NULL,
  `deal_value` varchar(255) DEFAULT NULL,
  `state` tinyint(2) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `done_date` datetime DEFAULT NULL,
  `op_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ins_audit_reason_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `order_id` varchar(255) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `state` tinyint(2) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
