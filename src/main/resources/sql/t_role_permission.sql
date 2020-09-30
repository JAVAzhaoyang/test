/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 100401
Source Host           : localhost:3306
Source Database       : car

Target Server Type    : MYSQL
Target Server Version : 100401
File Encoding         : 65001

Date: 2020-09-28 16:55:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `permission_id` varchar(255) DEFAULT NULL,
  `role_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('1', '1587c270-fed2-11ea-970f-54e1ad007360');
INSERT INTO `t_role_permission` VALUES ('2', '06674b47-f27f-11ea-970f-54e1ad007360');
INSERT INTO `t_role_permission` VALUES ('3', '06674b47-f27f-11ea-970f-54e1ad007360');
INSERT INTO `t_role_permission` VALUES ('4', '06674b47-f27f-11ea-970f-54e1ad007360');
INSERT INTO `t_role_permission` VALUES ('2', '4d94fbcd-f3fa-11ea-970f-54e1ad007360');
INSERT INTO `t_role_permission` VALUES ('2', 'cc1568e0-f404-11ea-970f-54e1ad007360');
