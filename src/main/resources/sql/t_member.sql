/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 100401
Source Host           : localhost:3306
Source Database       : car

Target Server Type    : MYSQL
Target Server Version : 100401
File Encoding         : 65001

Date: 2020-09-28 16:54:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_member
-- ----------------------------
DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member` (
  `id` varchar(255) NOT NULL,
  `createdtime` datetime DEFAULT NULL,
  `updatedtime` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  `deleteflag` int(11) DEFAULT NULL,
  `login_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `comcode` varchar(255) DEFAULT NULL,
  `org_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_member
-- ----------------------------
INSERT INTO `t_member` VALUES ('0f00d3645b2441ac9fb716769bf05760', '2020-09-28 15:50:27', '2020-09-28 15:50:27', '0', 'renyuan1', '业务测试人员', '15256720206', '842387321@qq.com', '江苏南京', '23000000', '03600cd6-f8bd-11ea-970f-54e1ad007360');
INSERT INTO `t_member` VALUES ('1', '2020-09-21 14:17:43', '2020-09-21 14:17:46', '0', 'admin', '管理员', '15256720206', '8423873221@qq.com', '江苏南京江宁', '23000000', '03600cd6-f8bd-11ea-970f-54e1ad007360');
INSERT INTO `t_member` VALUES ('167822df717b47f18428e355df74734d', '2020-09-28 15:08:15', '2020-09-28 15:18:25', '0', 'boss', '超级管理员', '15256720206', '842387321@qq.com', '江苏南京', '23000000', '03600cd6-f8bd-11ea-970f-54e1ad007360');
INSERT INTO `t_member` VALUES ('993107fde7824c7dad84e41c58eeff0b', '2020-09-28 15:50:44', '2020-09-28 15:50:44', '0', 'renyuan2', '运维测试人员', '15256720206', '842387321@qq.com', '江苏南京', '23000000', '03600cd6-f8bd-11ea-970f-54e1ad007360');
