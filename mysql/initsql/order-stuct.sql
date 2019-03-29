/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : order

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-03-26 11:23:23
*/

-- CREATE database order
DROP database IF EXISTS `ins_order`;
create database `ins_order` default character set utf8 collate utf8_general_ci;
-- change database
use ins_order;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ins_appli
-- ----------------------------
DROP TABLE IF EXISTS `ins_appli`;
CREATE TABLE `ins_appli` (
  `ins_appli_id` bigint(20) NOT NULL,
  `customer_name` varchar(1023) DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `group_address` varchar(255) DEFAULT NULL,
  `busi_contact_id` bigint(20) DEFAULT NULL,
  `busi_contact_phone` varchar(50) DEFAULT NULL,
  `state` tinyint(2) NOT NULL,
  `create_date` date DEFAULT NULL,
  `done_date` date DEFAULT NULL,
  `business_id` bigint(20) DEFAULT NULL,
  `creater` bigint(20) DEFAULT NULL,
  `ins_process_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `order_id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `update_date` date DEFAULT NULL,
  `state` tinyint(2) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
