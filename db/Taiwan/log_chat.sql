/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : l1jdb_taiwan

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2012-06-30 19:31:57
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `log_chat`
-- ----------------------------
DROP TABLE IF EXISTS `log_chat`;
CREATE TABLE `log_chat` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `account_name` varchar(50) NOT NULL,
  `char_id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `clan_id` int(10) NOT NULL,
  `clan_name` varchar(50) default NULL,
  `locx` int(10) NOT NULL,
  `locy` int(10) NOT NULL,
  `mapid` int(10) NOT NULL,
  `type` int(10) NOT NULL,
  `target_account_name` varchar(50) default NULL,
  `target_id` int(10) default '0',
  `target_name` varchar(50) default NULL,
  `target_clan_id` int(10) default NULL,
  `target_clan_name` varchar(50) default NULL,
  `target_locx` int(10) default NULL,
  `target_locy` int(10) default NULL,
  `target_mapid` int(10) default NULL,
  `content` varchar(256) NOT NULL,
  `datetime` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log_chat
-- ----------------------------
