/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 100401
Source Host           : localhost:3306
Source Database       : car

Target Server Type    : MYSQL
Target Server Version : 100401
File Encoding         : 65001

Date: 2020-09-28 16:54:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` varchar(255) NOT NULL,
  `createdtime` datetime DEFAULT NULL,
  `updatedtime` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  `deleteflag` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', '2020-09-10 15:16:38', '2020-09-16 09:57:03', '0', '*', '所有权限');
INSERT INTO `t_permission` VALUES ('2', '2020-09-11 15:50:52', '2020-09-15 15:47:53', '0', 'insert', '添加权限');
INSERT INTO `t_permission` VALUES ('3', '2020-09-11 15:51:38', '2020-09-15 15:47:38', '0', 'delete', '删除权限');
INSERT INTO `t_permission` VALUES ('4', '2020-09-11 15:51:38', '2020-09-15 15:47:48', '0', 'update', '修改权限');
