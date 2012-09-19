/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : l1jdb_taiwan

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2012-06-30 19:30:54
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `clan_data`
-- ----------------------------
DROP TABLE IF EXISTS `clan_data`;
CREATE TABLE `clan_data` (
  `clan_id` int(10) unsigned NOT NULL auto_increment,
  `clan_name` varchar(45) NOT NULL default '',
  `leader_id` int(10) unsigned NOT NULL default '0',
  `leader_name` varchar(45) NOT NULL default '',
  `hascastle` int(10) unsigned NOT NULL default '0',
  `hashouse` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`clan_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clan_data
-- ----------------------------
