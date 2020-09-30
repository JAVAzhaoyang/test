/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 100401
Source Host           : localhost:3306
Source Database       : car

Target Server Type    : MYSQL
Target Server Version : 100401
File Encoding         : 65001

Date: 2020-09-28 16:55:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `user_id` varchar(255) DEFAULT '',
  `role_id` varchar(255) DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('881e8729-f3fa-11ea-970f-54e1ad007360', '06674b47-f27f-11ea-970f-54e1ad007360');
INSERT INTO `t_user_role` VALUES ('881e8729-f3fa-11ea-970f-54e1ad007360', '4d94fbcd-f3fa-11ea-970f-54e1ad007360');
INSERT INTO `t_user_role` VALUES ('824573230ab848b0be7209e5ceaf7ec4', '1587c270-fed2-11ea-970f-54e1ad007360');
INSERT INTO `t_user_role` VALUES ('828731fb974a4b30bee5a07c2f733c41', '4d94fbcd-f3fa-11ea-970f-54e1ad007360');
INSERT INTO `t_user_role` VALUES ('6e9137a572dc4edba89b23d4a6868fe8', 'cc1568e0-f404-11ea-970f-54e1ad007360');
