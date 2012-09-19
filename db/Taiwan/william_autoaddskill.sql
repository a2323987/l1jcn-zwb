/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : l1jdb_taiwan

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2012-06-30 20:17:59
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `william_autoaddskill`
-- ----------------------------
DROP TABLE IF EXISTS `william_autoaddskill`;
CREATE TABLE `william_autoaddskill` (
  `Id` int(10) unsigned NOT NULL auto_increment,
  `Note` varchar(1000) default NULL,
  `Level` int(2) NOT NULL default '1',
  `SkillId` varchar(1000) default NULL,
  `Class` int(1) unsigned NOT NULL default '0',
  PRIMARY KEY  (`Id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of william_autoaddskill
-- ----------------------------
