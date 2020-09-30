/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 100401
Source Host           : localhost:3306
Source Database       : car

Target Server Type    : MYSQL
Target Server Version : 100401
File Encoding         : 65001

Date: 2020-09-28 16:53:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_car
-- ----------------------------
DROP TABLE IF EXISTS `t_car`;
CREATE TABLE `t_car` (
  `id` varchar(255) NOT NULL,
  `createdtime` datetime DEFAULT NULL,
  `updatedtime` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  `deleteflag` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `startdate` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  `enddate` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  `licenseno` varchar(11) DEFAULT '',
  `cost` decimal(10,2) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_car
-- ----------------------------
INSERT INTO `t_car` VALUES ('0eb3ecdaed5148b387639ec7d11e9de2', '2020-09-14 14:25:17', '2020-09-14 14:25:17', '0', '测试', '18', '2020-09-16 09:56:34', '2020-09-16 09:56:34', '88888', '6.00', '15256');
INSERT INTO `t_car` VALUES ('0ed4cc44712c453b852af208e44ca9ef', '2020-09-16 09:50:42', '2020-09-16 09:50:42', '1', '1212', '121', '2020-09-16 09:52:06', '2020-09-16 09:52:06', '12', '12.00', '12');
INSERT INTO `t_car` VALUES ('111', '2020-09-03 10:35:33', '2020-09-14 13:40:14', '0', '赵阳', '18', '2020-09-14 13:53:49', '2020-09-14 13:53:49', '皖A8423', '122.00', '15256720206');
INSERT INTO `t_car` VALUES ('84497ff17c1645e79cfe89e771d423b9', null, null, '0', '小明', '18', null, null, '88888', '18.88', '1.7775486663E10');
