/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 100401
Source Host           : localhost:3306
Source Database       : car

Target Server Type    : MYSQL
Target Server Version : 100401
File Encoding         : 65001

Date: 2020-09-28 16:55:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` varchar(255) NOT NULL,
  `createdtime` datetime DEFAULT NULL,
  `updatedtime` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  `deleteflag` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `insertsign` int(11) DEFAULT NULL,
  `deletesign` int(11) DEFAULT NULL,
  `updatesign` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('06674b47-f27f-11ea-970f-54e1ad007360', '2020-09-09 17:27:58', '2020-09-28 15:19:57', '0', '系统管理员', '0', '0', '0');
INSERT INTO `t_role` VALUES ('1587c270-fed2-11ea-970f-54e1ad007360', '2020-09-25 09:50:31', '2020-09-28 15:45:09', '0', '超级管理员', '0', '0', '0');
INSERT INTO `t_role` VALUES ('4d94fbcd-f3fa-11ea-970f-54e1ad007360', '2020-09-11 14:42:32', '2020-09-28 15:22:52', '0', '业务岗', '0', '1', '1');
INSERT INTO `t_role` VALUES ('cc1568e0-f404-11ea-970f-54e1ad007360', '2020-09-11 15:57:40', '2020-09-28 15:22:53', '0', '运维岗', '0', '1', '1');
