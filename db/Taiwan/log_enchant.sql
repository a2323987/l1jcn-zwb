/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : l1jdb_taiwan

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2012-06-30 19:32:03
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `log_enchant`
-- ----------------------------
DROP TABLE IF EXISTS `log_enchant`;
CREATE TABLE `log_enchant` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `char_id` int(10) NOT NULL default '0',
  `item_id` int(10) unsigned NOT NULL default '0',
  `old_enchantlvl` int(3) NOT NULL default '0',
  `new_enchantlvl` int(3) default '0',
  PRIMARY KEY  (`id`),
  KEY `key_id` (`char_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log_enchant
-- ----------------------------
