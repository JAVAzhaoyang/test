/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 100401
Source Host           : localhost:3306
Source Database       : car

Target Server Type    : MYSQL
Target Server Version : 100401
File Encoding         : 65001

Date: 2020-09-28 16:54:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_org
-- ----------------------------
DROP TABLE IF EXISTS `t_org`;
CREATE TABLE `t_org` (
  `id` varchar(255) NOT NULL,
  `createdtime` datetime DEFAULT NULL,
  `updatedtime` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  `deleteflag` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `comcode` varchar(255) DEFAULT '',
  `pcode` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_org
-- ----------------------------
INSERT INTO `t_org` VALUES ('03600cd6-f8bd-11ea-970f-54e1ad007360', '2020-09-17 16:05:36', '2020-09-21 11:09:52', '0', '江苏省总公司', '23000000', '00000000', '0');
INSERT INTO `t_org` VALUES ('37defbb0-f8be-11ea-970f-54e1ad007360', '2020-09-17 16:14:13', '2020-09-17 16:14:13', '0', '江宁双龙大道营业部', '23010301', '23010300', '3');
INSERT INTO `t_org` VALUES ('37e1c43c-f8be-11ea-970f-54e1ad007360', '2020-09-17 16:14:13', '2020-09-17 16:14:13', '0', '江宁百家湖营业部', '23010302', '23010300', '3');
INSERT INTO `t_org` VALUES ('67bf9bae-f8bd-11ea-970f-54e1ad007360', '2020-09-17 16:08:24', '2020-09-17 16:12:59', '0', '南京市分公司', '23010000', '23000000', '1');
INSERT INTO `t_org` VALUES ('67c32c01-f8bd-11ea-970f-54e1ad007360', '2020-09-17 16:08:24', '2020-09-23 16:12:50', '0', '苏州市分公司', '23020000', '23000000', '1');
INSERT INTO `t_org` VALUES ('67c6670a-f8bd-11ea-970f-54e1ad007360', '2020-09-17 16:08:24', '2020-09-23 16:12:41', '0', '徐州市分公司', '23030000', '23000000', '1');
INSERT INTO `t_org` VALUES ('dddcdc56-f8bd-11ea-970f-54e1ad007360', '2020-09-17 16:11:42', '2020-09-17 16:13:07', '0', '南京市建邺支公司', '23010100', '23010000', '2');
INSERT INTO `t_org` VALUES ('dddf96f2-f8bd-11ea-970f-54e1ad007360', '2020-09-17 16:11:42', '2020-09-17 16:13:08', '0', '南京市秦淮支公司', '23010200', '23010000', '2');
INSERT INTO `t_org` VALUES ('dde2e6e7-f8bd-11ea-970f-54e1ad007360', '2020-09-17 16:11:42', '2020-09-17 16:13:10', '0', '南京市江宁支公司', '23010300', '23010000', '2');
