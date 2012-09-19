/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : l1jdb_taiwan

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2012-06-30 19:30:42
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `character_teleport`
-- ----------------------------
DROP TABLE IF EXISTS `character_teleport`;
CREATE TABLE `character_teleport` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `char_id` int(10) unsigned NOT NULL default '0',
  `name` varchar(45) NOT NULL default '',
  `locx` int(10) unsigned NOT NULL default '0',
  `locy` int(10) unsigned NOT NULL default '0',
  `mapid` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`id`),
  KEY `key_id` (`char_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of character_teleport
-- ----------------------------
