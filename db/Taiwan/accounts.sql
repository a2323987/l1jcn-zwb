/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : l1jdb_taiwan

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2012-06-30 19:29:36
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `accounts`
-- ----------------------------
DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts` (
  `login` varchar(50) NOT NULL default '',
  `password` varchar(50) default NULL,
  `lastactive` datetime default NULL,
  `access_level` int(11) default NULL,
  `ip` varchar(20) NOT NULL default '',
  `host` varchar(255) NOT NULL default '',
  `online` int(11) NOT NULL default '0',
  `banned` int(11) unsigned NOT NULL default '0',
  `character_slot` int(2) unsigned NOT NULL default '0',
  `warepassword` int(6) unsigned NOT NULL default '0',
  `OnlineStatus` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`login`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of accounts
-- ----------------------------
