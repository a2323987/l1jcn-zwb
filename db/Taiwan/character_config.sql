/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : l1jdb_taiwan

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2012-06-30 19:30:20
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `character_config`
-- ----------------------------
DROP TABLE IF EXISTS `character_config`;
CREATE TABLE `character_config` (
  `object_id` int(10) NOT NULL default '0',
  `length` int(10) unsigned NOT NULL default '0',
  `data` blob,
  PRIMARY KEY  (`object_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of character_config
-- ----------------------------
