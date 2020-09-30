/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 100401
Source Host           : localhost:3306
Source Database       : car

Target Server Type    : MYSQL
Target Server Version : 100401
File Encoding         : 65001

Date: 2020-09-28 16:54:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_master_member
-- ----------------------------
DROP TABLE IF EXISTS `t_master_member`;
CREATE TABLE `t_master_member` (
  `id` varchar(255) NOT NULL,
  `createdtime` datetime DEFAULT NULL,
  `updatedtime` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  `deleteflag` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `create_id` varchar(255) DEFAULT NULL,
  `member_id` varchar(255) DEFAULT '',
  `org_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_master_member
-- ----------------------------
INSERT INTO `t_master_member` VALUES ('1', '2020-09-21 17:04:35', '2020-09-23 14:07:00', '0', '0', '881e8729-f3fa-11ea-970f-54e1ad007360', '1', '03600cd6-f8bd-11ea-970f-54e1ad007360');
INSERT INTO `t_master_member` VALUES ('6e8e19451daa450b959ef4b4b5a09ee6', '2020-09-28 15:52:22', '2020-09-28 15:52:22', '0', '1', '167822df717b47f18428e355df74734d', '0f00d3645b2441ac9fb716769bf05760', '03600cd6-f8bd-11ea-970f-54e1ad007360');
INSERT INTO `t_master_member` VALUES ('a53bcb38bd72425caa018dcb230146a8', '2020-09-28 15:52:33', '2020-09-28 15:52:33', '0', '2', '167822df717b47f18428e355df74734d', '993107fde7824c7dad84e41c58eeff0b', '03600cd6-f8bd-11ea-970f-54e1ad007360');
