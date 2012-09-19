/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : l1jdb_taiwan

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2012-06-30 19:32:30
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `pets`
-- ----------------------------
DROP TABLE IF EXISTS `pets`;
CREATE TABLE `pets` (
  `item_obj_id` int(10) unsigned NOT NULL default '0',
  `objid` int(10) unsigned NOT NULL default '0',
  `npcid` int(10) unsigned NOT NULL default '0',
  `name` varchar(45) NOT NULL default '',
  `lvl` int(10) unsigned NOT NULL default '0',
  `hp` int(10) unsigned NOT NULL default '0',
  `mp` int(10) unsigned NOT NULL default '0',
  `exp` int(10) unsigned NOT NULL default '0',
  `lawful` int(10) unsigned NOT NULL default '0',
  `food` int(2) NOT NULL default '0',
  PRIMARY KEY  (`item_obj_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pets
-- ----------------------------
