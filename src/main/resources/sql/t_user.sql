/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 100401
Source Host           : localhost:3306
Source Database       : car

Target Server Type    : MYSQL
Target Server Version : 100401
File Encoding         : 65001

Date: 2020-09-28 16:55:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(255) NOT NULL,
  `createdtime` datetime DEFAULT NULL,
  `updatedtime` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  `deleteflag` int(11) DEFAULT NULL,
  `login_name` varchar(255) DEFAULT '',
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `fail_times` int(11) DEFAULT NULL,
  `locked` tinyint(4) DEFAULT NULL,
  `lockedtime` datetime DEFAULT NULL,
  `org_id` varchar(255) DEFAULT '',
  `member_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('6e9137a572dc4edba89b23d4a6868fe8', '2020-09-28 15:50:44', '2020-09-28 15:50:44', '0', 'renyuan2', '运维测试人员', 'ebb741bf9b56a93d01aa54b10674238d0e49ffcf', '13e0d493c335b4ec', '0', '0', null, '03600cd6-f8bd-11ea-970f-54e1ad007360', '993107fde7824c7dad84e41c58eeff0b');
INSERT INTO `t_user` VALUES ('824573230ab848b0be7209e5ceaf7ec4', '2020-09-28 15:08:15', '2020-09-28 15:18:21', '0', 'boss', '超级管理员', 'c6dcdbcd2e7e12a2a11a12ceaed5dd0e87d613f8', 'b14c679c25b7b693', '0', '0', null, '03600cd6-f8bd-11ea-970f-54e1ad007360', '167822df717b47f18428e355df74734d');
INSERT INTO `t_user` VALUES ('828731fb974a4b30bee5a07c2f733c41', '2020-09-28 15:50:27', '2020-09-28 15:50:27', '0', 'renyuan1', '业务测试人员', 'e6ab584c3b20aab378af53e930dda64bfbd745bf', '0aaeb9b1faa6af1b', '0', '0', null, '03600cd6-f8bd-11ea-970f-54e1ad007360', '0f00d3645b2441ac9fb716769bf05760');
INSERT INTO `t_user` VALUES ('881e8729-f3fa-11ea-970f-54e1ad007360', '2020-09-01 16:55:37', '2020-09-21 16:13:56', '0', 'admin', '管理员', '9b9f8b5804bf72cca96714a5ca834a59c6788e16', '22ce3f6645791710', '0', '0', null, '03600cd6-f8bd-11ea-970f-54e1ad007360', '1');
